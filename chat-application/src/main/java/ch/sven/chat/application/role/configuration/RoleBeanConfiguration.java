package ch.sven.chat.application.role.configuration;

import ch.sven.chat.domain.role.repository.RoleRepository;
import ch.sven.chat.domain.role.service.RoleService;
import ch.sven.chat.domain.role.service.RoleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RoleBeanConfiguration {
    @Bean
    public RoleService roleService(RoleRepository roleRepository) {
        return new RoleServiceImpl(roleRepository);
    }
}
