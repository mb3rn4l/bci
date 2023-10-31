package domain.service

import com.bci.bci.layer.domain.constants.UserConstants
import com.bci.bci.layer.domain.exception.InvalidDataException
import com.bci.bci.layer.domain.exception.UserAlreadyExistException
import com.bci.bci.layer.domain.model.User
import com.bci.bci.layer.domain.service.SignUpService
import com.bci.bci.layer.infrastructure.adapter.repository.UserJpaAdapter
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

class SignUpServiceSpec extends Specification {

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

    @Unroll
    void 'campos obligatorios: email - #email , password - #password '() {
        given: 'no han ingresado uno de los campos obligatorios'

        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email(email)
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
        thrown(expectedException)

        where:
        email               || password       || expectedException
        'prueba@prueba.com' || null           || InvalidDataException
        null                || "a2asfGfdfdf4" || InvalidDataException

    }
}
