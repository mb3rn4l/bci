package domain.model

import com.bci.bci.layer.domain.exception.InvalidDataException
import com.bci.bci.layer.domain.model.User
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

class UserPasswordTest extends Specification {

    void 'password valida'() {
        given: 'han ingresado una password con formato valido'

        def password = "a2asfGfdfdf4"

        when: 'creamos un usuario'

        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email("prueba@prueba.com")
                .password(password)
                .build()

        then: 'el usuario es creado correctamente'

        password == user.password
    }

    @Unroll
    void 'password #password invalida'() {
        given: 'han ingresado una password con formato invalido'

        when: 'creamos un usuario'
        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email("prueba@prueba.com")
                .password(password)
                .build()

        then: 'se lanza una excepcion'
        def e = thrown(expectedException)
        e.message == User.INVALID_PASSWORD_FORMAT

        where:
        password              || expectedException
        'a2a8fGfdfdf4'        || InvalidDataException
        'a2aEfGfdfdf4'        || InvalidDataException
        'a2afGf'              || InvalidDataException
        'a2afGfdfdf4sfsffjsl' || InvalidDataException
    }
}
