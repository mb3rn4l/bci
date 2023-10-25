package domain.service

import com.bci.bci.layer.domain.exception.UserAlreadyExistException
import com.bci.bci.layer.domain.model.User
import com.bci.bci.layer.domain.service.SignUpService
import com.bci.bci.layer.infrastructure.adapter.repository.UserJpaAdapter
import spock.lang.Specification

import java.time.LocalDate

class SignUpServiceTest extends Specification {

    void 'Usuario creado correctamente'() {
        given: 'se quiere registrar un usuario'

        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email("prueba@prueba.com")
                .password("a2asfGfdfdf4")
                .build()

        def stubbedExistUserRepository = Stub(UserJpaAdapter) {
            exist(_) >> false
        }

        def mockedSaveUserRepository = Mock(UserJpaAdapter)
        def signUpService = new SignUpService(mockedSaveUserRepository, stubbedExistUserRepository);

        when: 'se llama al servicio para que ejecute la logica'

        signUpService.execute(user)

        then: 'el usuario es creado correctamente'

        1 * mockedSaveUserRepository.save(_)
    }

    void 'Usuario ya existe'() {

        given: 'ya existe un usuario con email: prueba@prueba.com y otro usuario se quiere registrar con el mismo email'

        def stubbedExistUserRepository = Stub(UserJpaAdapter) {
            exist(_) >> true
        }

        def mockedSaveUserRepository = Mock(UserJpaAdapter)
        def signUpService = new SignUpService(mockedSaveUserRepository, stubbedExistUserRepository);

        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email("prueba@prueba.com")
                .password("a2asfGfdfdf4")
                .build()

        when: 'se llama al servicio para que ejecute la logica'

        signUpService.execute(user)

        then: 'se lanza una excepcion'
        thrown(UserAlreadyExistException)
    }
}
