package domain.service

import com.bci.bci.layer.domain.exception.InvalidDataException
import com.bci.bci.layer.domain.model.User
import com.bci.bci.layer.domain.service.SignUpService
import com.bci.bci.layer.infrastructure.adapter.repository.UserJpaAdapter
import spock.lang.Specification
import spock.lang.Unroll

import java.time.LocalDate

class UserRequiredFields extends Specification {
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
