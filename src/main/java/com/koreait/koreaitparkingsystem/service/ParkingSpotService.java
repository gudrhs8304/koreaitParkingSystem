package com.koreait.koreaitparkingsystem.service;

import com.koreait.koreaitparkingsystem.dao.ParkingLogDAO;
import com.koreait.koreaitparkingsystem.dao.ParkingSpotDAO;
import com.koreait.koreaitparkingsystem.dto.CarDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingLogDTO;
import com.koreait.koreaitparkingsystem.dto.ParkingSpotDTO;
import com.koreait.koreaitparkingsystem.util.MapperUtil;
import com.koreait.koreaitparkingsystem.vo.ParkingLogVO;
import com.koreait.koreaitparkingsystem.vo.ParkingSpotVO;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

/**
 * ✅ ParkingSpotService
 * - 주차공간(스팟) 조회 및 관리 서비스
 * - 전체 주차구역 조회, 출차시 자리 해제 등 주차장 상태 변경 처리
 */

@Log4j2
public enum ParkingSpotService {
    INSTANCE;

    private final ParkingSpotDAO parkingSpotDAO = ParkingSpotDAO.INSTANCE;
    private final ModelMapper modelMapper = MapperUtil.INSTANCE.getInstance();
    private final ParkingLogDAO parkingLogDAO = ParkingLogDAO.INSTANCE;


    /*
     * ✅ 모든 주차구역 목록 조회
     * DAO를 통해 ParkingSpotVO 목록을 가져오고
     * ModelMapper로 ParkingSpotDTO 리스트로 변환해서 반환함.
     */
    public List<ParkingSpotDTO> getParkingSpots() {

        // 1) DAO에서 VO리스트 가져옴
        List<ParkingSpotVO> parkingSpotVOS = parkingSpotDAO.selectAllParkingSpots();

        // 2) VO -> DTO 리스트로 변환 후 반환
        Type listType = new TypeToken<List<ParkingSpotDTO>>() {}.getType();
        return modelMapper.map(parkingSpotVOS, listType); // 값을 반환 인라인화.
    }

    /*
     * ✅ 차량번호 포함 모든 주차구역 목록 조회
     * - DAO의 selectAllParkingSpotsWithCarNumber() 호출
     * - ModelMapper로 ParkingSpotDTO 리스트로 변환 후 반환
     */
    public List<ParkingSpotDTO> getParkingSpotsWithCarNumber() {
        List<ParkingSpotVO> parkingSpotVOS = parkingSpotDAO.selectAllParkingSpotsWithCarNumber();
        Type listType = new TypeToken<List<ParkingSpotDTO>>() {}.getType();
        return modelMapper.map(parkingSpotVOS, listType);
    }

    /*
     * ✅ 출차 처리된 차량이면 주차공간을 '빈 상태'로 업데이트
     * - 차량번호로 ParkingLog의 마지막 기록 가져옴
     * - 출차시간이 있으면 DAO로 주차스팟 release
     * - 없으면 로그만 출력
     */
    public void isParkingSpot(CarDTO carDTO) {
        // 1) 마지막 ParkingLog 가져오기
        ParkingLogVO logVO = parkingLogDAO.selectLastLogByCarNumber(carDTO.getCarNumber());
        ParkingLogDTO logDTO = null;
        if (logVO != null) {
            logDTO = modelMapper.map(logVO, ParkingLogDTO.class);
        }

        // 2) 출차되어 있으면 주차공간 해제
        if (logDTO != null && logDTO.getOutTime() != null) {
            int spotNumber = logDTO.getParkingSpot();
            parkingSpotDAO.releaseSpot(spotNumber);
            log.info("ParkingSpotService 47 주차 구역 " + spotNumber + "이(가) 출차되어 사용 가능 상태로 변경되었습니다.");
        } else {
            log.info("ParkingSpotService 49 출차 로그를 찾을 수 없거나 아직 출차되지 않았습니다.");
        }
    }
}
