import com.yevdo.jwildcard.JWildcard
import spock.lang.Specification


/**
 * @author Yevdo Abramov
 * Created on 26/03/2018
 */
class JWildcardSpec extends Specification {

    def "convert wildcard to regex"() {
        when:
        String regex = JWildcard.wildcardToRegex("mywil?card*")

        then:
        regex == "^\\Qmywil\\E.\\Qcard\\E.*\$"

        when:
        regex = JWildcard.wildcardToRegex("mywil?card*", false)

        then:
        regex == "\\Qmywil\\E.\\Qcard\\E.*"

        when:
        regex = JWildcard.wildcardToRegex("?card*", false)

        then:
        regex == ".\\Qcard\\E.*"

        when:
        regex = JWildcard.wildcardToRegex("?card*wild", false)

        then:
        regex == ".\\Qcard\\E.*\\Qwild\\E"
    }

    def "check matcher"() {
        when:
        boolean matches = JWildcard.matches("mywild*", "mywildcard")

        then:
        matches

        when:
        matches = JWildcard.matches("mywild*", "mywilcard")

        then:
        !matches

        when:
        matches = JWildcard.matches("mywildcard", "mywild*")

        then:
        !matches
    }
}