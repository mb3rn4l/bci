package domain.model

import com.bci.bci.layer.domain.exception.InvalidDataException
import com.bci.bci.layer.domain.model.User
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

class UserRequiredFields extends Specification {
    @Unroll
    void 'campos obligatorios: email - #email , password - #password '() {
        given: 'no han ingresado uno de los campos obligatorios'
        when: 'creamos un usuario'
        def user = User.builder()
                .created(LocalDate.now())
                .lastLogin(null)
                .token("token")
                .isActive(true)
                .name("name")
                .email(email)
                .password(password)
                .build()

        then: 'se lanza una excepcion'
        thrown(expectedException)

        where:
        email               || password       || expectedException
        'prueba@prueba.com' || null           || InvalidDataException
        null                || "a2asfGfdfdf4" || InvalidDataException

    }
}
