package bg.tu_varna.sit.courseproject30.business.services;

import bg.tu_varna.sit.courseproject30.data.repositorities.CityRepository;

public class CityService {
    private final CityRepository repository = CityRepository.getInstance();

    public static CityService getInstance() {
        return CityService.CityServiceHolder.INSTANCE;
    }

    private static class CityServiceHolder {
        public static final CityService INSTANCE = new CityService();
    }
}
