package domain.service

import com.bci.bci.layer.domain.exception.NotFoundException
import com.bci.bci.layer.domain.model.User
import com.bci.bci.layer.domain.service.LoginService
import com.bci.bci.layer.infrastructure.adapter.repository.UserJpaAdapter
import com.bci.bci.layer.infrastructure.adapter.utils.JwtUtil
import spock.lang.Specification

import java.time.LocalDate

class LoginServiceSpec extends Specification {
    void 'Usuario consultado correctamente'() {
        given: 'se consulta un usuario por token'

        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email("prueba@prueba.com")
                .password("a2asfGfdfdf4")
                .build()


        def stubbedUserRepository = Stub(UserJpaAdapter) {
            findByToken(_) >> { user };
        }

        def token = "token";
        def jwt = new JwtUtil();
        jwt.secret = "secret"
        def mockedSaveUserRepository = Mock(UserJpaAdapter)
        def loginService = new LoginService(stubbedUserRepository, mockedSaveUserRepository, jwt)


        when: 'se llama al servicio para que ejecute la logica'

        loginService.execute(token)

        then: 'el usuario es retornado con un nuevo token'
        token != user.token
        user.lastLogin != null
    }

    void 'Usuario no existe'() {

        given: 'se consulta un usuario por token'

        def stubbedUserRepository = Stub(UserJpaAdapter) {
            findByToken(_) >> { throw new NotFoundException() };
        }

        def token = "token";
        def jwt = new JwtUtil();
        def mockedSaveUserRepository = Mock(UserJpaAdapter)
        def loginService = new LoginService(stubbedUserRepository, mockedSaveUserRepository, jwt);

        when: 'se llama al servicio para que ejecute la logica'

        loginService.execute(token)

        then: 'se lanza una excepcion'
        thrown(NotFoundException)
    }
}