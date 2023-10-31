package domain.service

import com.bci.bci.layer.domain.constants.UserConstants
import com.bci.bci.layer.domain.exception.InvalidDataException
import com.bci.bci.layer.domain.model.User
import com.bci.bci.layer.domain.service.SignUpService
import com.bci.bci.layer.infrastructure.adapter.repository.UserJpaAdapter
import spock.lang.Specification

import java.time.LocalDate

class UserEmailTest extends Specification {

    void 'formato de email valido'() {
        given: 'han ingresado un email con formato valido'

        def email = "prueba@prueba.com"

        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email(email)
                .password("a2asfGfdfdf4")
                .build()

        def stubbedExistUserRepository = Stub(UserJpaAdapter) {
            exist(_) >> false
        }

        def mockedSaveUserRepository = Mock(UserJpaAdapter)
        def signUpService = new SignUpService(mockedSaveUserRepository, stubbedExistUserRepository);

        when: 'creamos un usuario'

        signUpService.execute(user)

        then: 'el usuario es creado correctamente'

        email == user.email
    }

    void 'formato de email invalido'() {
        given: 'han ingresado un email con formato invalido'

        def email = "prueba.gmail.com"

        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email(email)
                .password("a2asfGfdfdf4")
                .build()

        def stubbedExistUserRepository = Stub(UserJpaAdapter) {
            exist(_) >> false
        }

        def mockedSaveUserRepository = Mock(UserJpaAdapter)
        def signUpService = new SignUpService(mockedSaveUserRepository, stubbedExistUserRepository);

        when: 'creamos un usuario'

        signUpService.execute(user)

        then: 'se lanza una excepcion'
        def e = thrown(InvalidDataException)
        e.message == UserConstants.INVALID_EMAIL_FORMAT
    }
}
