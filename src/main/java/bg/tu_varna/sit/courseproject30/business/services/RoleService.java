package bg.tu_varna.sit.courseproject30.business.services;


import bg.tu_varna.sit.courseproject30.data.repositorities.RoleRepository;

public class RoleService{
    private final RoleRepository repository = RoleRepository.getInstance();

    public static RoleService getInstance() {
        return RoleService.RoleServiceHolder.INSTANCE;
    }

    private static class RoleServiceHolder {
        public static final RoleService INSTANCE = new RoleService();
    }
}
