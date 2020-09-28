import com.xmbsmdsj.Client
import org.junit.Before
import org.junit.Test

class TestClient {

    @Test
    fun testOneCallCurrent(){
        var oneCallApi = Client("").oneCallApi()

        val res = oneCallApi.currentWeather(27.2048, 77.4975).execute()
        println(res)
    }


}