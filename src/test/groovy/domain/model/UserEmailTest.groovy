package domain.model

import com.bci.bci.layer.domain.exception.InvalidDataException
import com.bci.bci.layer.domain.model.User
import spock.lang.Specification

import java.time.LocalDate

class UserEmailTest extends Specification {

    void 'formato de email valido'() {
        given: 'han ingresado un email con formato valido'

        def email = "prueba@prueba.com"

        when: 'creamos un usuario'

        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email(email)
                .password("a2asfGfdfdf4")
                .build()

        then: 'el usuario es creado correctamente'

        email == user.email
    }

    void 'formato de email invalido'() {
        given: 'han ingresado un email con formato invalido'

        def email = "prueba.gmail.com"

        when: 'creamos un usuario'
        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email(email)
                .password("a2asfGfdfdf4")
                .build()

        then: 'se lanza una excepcion'
        def e = thrown(InvalidDataException)
        e.message == User.INVALID_EMAIL_FORMAT
    }
}
