import com.xmbsmdsj.Client
import org.junit.Before
import org.junit.Ignore
import org.junit.Test

//@Ignore
class TestClient {

    @Test
    @Ignore
    fun testOneCallCurrent(){
        var oneCallApi = Client("").oneCallApi()

        val res = oneCallApi.currentWeather(27.2048, 77.4975).execute()
        println(res)
    }

    @Test
    fun testSingleton(){
        Client("").oneCallApi()
        Client("").oneCallApi()
        Client("").oneCallApi()
        Client("").oneCallApi()
        Client("").oneCallApi()
        Client("").oneCallApi()

    }



}