package domain.service

import com.bci.bci.layer.domain.constants.UserConstants
import com.bci.bci.layer.domain.exception.InvalidDataException
import com.bci.bci.layer.domain.model.User
import com.bci.bci.layer.domain.service.SignUpService
import com.bci.bci.layer.infrastructure.adapter.repository.UserJpaAdapter
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

class UserPasswordTest extends Specification {

    void 'password valida'() {
        given: 'han ingresado una password con formato valido'

        def password = "a2asfGfdfdf4"

        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email("prueba@prueba.com")
                .password(password)
                .build()

        def stubbedExistUserRepository = Stub(UserJpaAdapter) {
            exist(_) >> false
        }

        def mockedSaveUserRepository = Mock(UserJpaAdapter)
        def signUpService = new SignUpService(mockedSaveUserRepository, stubbedExistUserRepository);

        when: 'creamos un usuario'

        signUpService.execute(user)

        then: 'el usuario es creado correctamente'

        password == user.password
    }

    @Unroll
    void 'password #password invalida'() {
        given: 'han ingresado una password con formato invalido'

        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email("prueba@prueba.com")
                .password(password)
                .build()

        def stubbedExistUserRepository = Stub(UserJpaAdapter) {
            exist(_) >> false
        }

        def mockedSaveUserRepository = Mock(UserJpaAdapter)
        def signUpService = new SignUpService(mockedSaveUserRepository, stubbedExistUserRepository);

        when: 'creamos un usuario'

        signUpService.execute(user)

        then: 'se lanza una excepcion'
        def e = thrown(expectedException)
        e.message == UserConstants.INVALID_PASSWORD_FORMAT

        where:
        password              || expectedException
        'a2a8fGfdfdf4'        || InvalidDataException
        'a2aEfGfdfdf4'        || InvalidDataException
        'a2afGf'              || InvalidDataException
        'a2afGfdfdf4sfsffjsl' || InvalidDataException
    }
}
