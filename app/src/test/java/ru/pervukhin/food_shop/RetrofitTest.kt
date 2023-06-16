package ru.pervukhin.food_shop

import com.google.gson.Gson
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.pervukhin.food_shop.data.internet.DishesService
import ru.pervukhin.food_shop.data.internet.model.DishData

class RetrofitTest {
    lateinit var mockWebServer: MockWebServer
    lateinit var dishesService: DishesService
    val json =
        "{ \"dishes\":[{\"id\": 1,\"name\": \"Рис с овощами\",\"price\": 799,\"weight\": 560,\"description\": \"В китайской кухне рис с овощами готовят с добавлением грибов, бамбуковых побегов, моркови, лука и гороха. Эти ингредиенты быстро обжариваются на сковороде с соевым соусом и специями.\",\"image_url\": \"https://lh3.googleusercontent.com/fife/APg5EOb3-gGunBAb_3E7L5qZLGIx0Wm8kh4UjNn2yow-7Kvf50D7eFb9Iw5g_7W7TQLGKF29-G6VNa7dHS_zEWY8VaSMh9EqUql8UEFISB_WWgiO8nf_mt0YtUhsWFB5uzw-Bfi_eS9Cs-0vLUMiqaqTGgGFDvVhUvak4AypPMEbt2-3mEkxeZNcClEy29x8gEmUU6e9G8s5GDyCxR404OIsgnGHqtIaGIGD7afRoz7PtgtmZdlXC5v7dHujDNh5l28v249qxjpa1rqxoorBb-ywkRsu1bzqDEHGbRDnFNLXHwEGlHoLS1krz9KcD3opkmQckg7-m7PXzEhnQlayqMPiNGP-WpnFYrthFVfJ0TY4zsYFx1azSJZTLa59Xuqr32eagNn9xF6mCPpGqRbunBKrbD-oWidQ_iAXZRRrgjZrv280Joe8z73AluN0A-mXcqRkpVwQo1n3szZ00wX7sD44PriRwHGUfEenTC5IyLVv3MBsYMNXJ9ALa6FZgTrnUhO_ePGoPftELYGNp4yn-xkzfM_GvnQR8A6od0bR8AqqpNketd0kBavJTkaJXBwsfvxinTdLfOTfWsZAhF97XYNEA_9SmTgnnOjt3N8YUxmUeWcor174r7bNdDuDQqq7vWRgFZNorh5v6LANRk0CVsq6B9tVxP2R1zTCc1yXgpu4kNGJsEvNyxMRy-yM3cBvhu01ZUFSalvwAqcS2M9_eBoPCu00KVtcEvyFPoqm_QNHBEkDLjUdtJC7BGcmv7SPa-rV6oH_3zeIYstyKLrgN-Dzewe816A6J7IN4YxSvCIOWbNV9Q6O3hxua_ZrGSk6ijSoBKE0XHUC04cr1O6BzRxL9lwVUhzvBzYfzmBzMPb84Pq-WwBtfCxN2j34NReGu5iABuA1iDNgz0r8WE2Dvvz0XsD6Uc8neX57A7_19J6vyJEhuARJREXGs4tFWHMmjqg-xwYOOPJhxF1BkCaQrUEKtFYBq1pT2N7_h9fMV8JOLP92grJFHRP6TmNrEjPAf9HjfYRFouBAqFMRy614VrJu5hyweBcy-4WbJNYqrvKZH_bXIQyZ6qlv4omHhGTSMMT9cAYTyiMm12bEH5ccThAygPaXlfx6ydA3towLnMpoq0ieByM2-Nql2uh4xPxgAHcmzipRgEqlYDflDKNSfeTVFKKQ4vtTWa43wR505BTjdO3mk5CVoK4sOzwcF1mQA2joVXdW63wbUWtw4wtfa3e9EP1TV01b5M02KKPcr2yxZpNQCo8-Igp6M8t_vqWSKlkq-Z7NaRswW-xQyuFWjCufwgpd1m8i5Z4tnL72DhelmIiI2cXufJb70_eobyMV5VsMUab1nLKWoKwggolnEjl2A1PLrv6T0aRTYmjt3JqFDEV58aIjZhhyrcX6h6Bb_AJzc-OFvFPw1uAuswJaimihZUfYoVuwtNRI27BD7KGnXzMHKthOM4fArK3ICIt61g91DNHAu9qhpWxsj0FrcmujdWE2vJpBf7XoOcgxiRlRKpljQ2c3M6ULTdS1nuypZleouOtwIHOUoxGtT5HJeC8ZcsgI3przMt97iGmSv5Us7xL0j42wVNZbQnyQ6EsIyIG1ZOo2ah9CyA7RqixQUevdzbPhSnJk2w6weuuRCDkdC4H97doLyAV_=w1366-h617\",\"tegs\":[\"Все меню\", \"С рисом\", \"Салаты\"] }," +
                "{\"id\": 2,\"name\": \"Салат по восточному\",\"price\": 699,\"weight\": 360,\"description\": \"Салат по восточному - это здоровое и вкусное блюдо, которое мы готовим в нашем ресторане. Он состоит из свежей зелени, овощей, таких как огурцы, помидоры, красный лук и оливки, а также сыра фета и пряных специй, которые придают ему уникальный вкус и аромат. Наше блюдо хорошо сочетается с другими блюдами нашего меню, а также может служить отдельным легким обедом или перекусом.\",\"image_url\": \"https://lh3.googleusercontent.com/fife/APg5EOafwbErJPvjpg_yUmo7-sLRLWZF08C8k6VCCW3lGRyMVGbSnmfUxl1sNzL0oVfwT2cBZDlCMhNi6XBmrMU3zfX_wCbuT3dXN-dVGbl5ccr0NZyKwmee6geavwEEjDPIpAI0TSSoSIW5ESD42sxXblAKnt4vpETfa4MZYRBj6_0FIY9z2iPJHViTigN-vDdCPnq5RgC3f2-HZ1Algs_e5iSbauXwknae6sINa8JiZY9XdGT-qwsXivIrNakp5X4SkYUXTPfm4B9iBBuoO2SlLzDmPU8i6gglA1hNCM6ou2A0OK4x-to0plYfCz3Gpbl3c42iNqyBDi6wgiEKNP5DwyMCwrlQvfCdiXOinp6zXHqGBhV-54nl1bySMOB7oTd-iIIiG9b-P6OkNdpeMXb6INgW2looGoM_LXbEoD9HZ_QsopwtqGF5eDbeNTm4n6D7wiZX4rCpkukKNhS48Ne-cUIPAonEs0LfBEReGhiPBAsieaGqKxCS3ovW93w72qsGehL6hg_Wmxe_LOkwu-UHXyjPbUX8LfMLcL0ZdME2VOSkNnqbnR70TPBk3SJHlH3UOfsEzkxra1voEtM9hVJHgiX8kPm5Ym2V7CPYIXxaAN268ui47S07NUu1vhTOrYcdmn2zmJR8k0l1-zcG1WAx1fgKbnqbtezZ9VfYKRlLAtlsD8h86C4yUhQt74DkbfENrHZmLzVORTlYCi40wlIlAL1UsOzvR26163HYl-ZkRA-71199d3Dxr2T2Nv5IPoJmDuJACqu6FzxT71t_Q3-ZKPzNgt7fGpDf_4Xc9dC7G9lvQKpCMv8d3AVkZjf6czQoETmC7Hf6-9GdAp4jqGGztYRmR7wN11mrucEUzg63Mh5hh5bmbt93SctR_gnlt6rbyqo1aYu0p7xw6z02kyQWspnCPpinOMxLmeOI0TIsaqjq7-2VMWD04vMsYA1uVY3gYaST8IXA62ltb1ngSF6A4Qo3FtBgm1LS2yC-KxywiWSCCWwuv0WOc79dUxMl22XcUt4J_bIB0uyjIOmqD-uPGzger-dP6ZiM8_rBhNz40pJ0gWblxWoc6tpWgbhpTPBCCK7e91t3_pmlbh56EQrsKycQHHfTIVe7clQblWHI-Y9qX12WW5jn8QgmAbeAcjEBiqGLfMpWWdCs97HTZAgNYXhikTBXcqCRayvTvZRK5SiNeDzcxSdpLRyG72H6glEyYBL96KuSB1VuylT1APbBcABbDzEvTyNZWZER_NQwu9X5qDxeLmTwyHr0xq_igNaFxMxYDyYqiaAaIuFwG5yQ9d8fnMVo4QYa1HjoB8vVJrHtd2EJltEKe8KgVxjAVQyaSI7ZWwAzJXt4_3bsIgqKzY8ZLdY-xQHtorLRa_GaCaX-KasXOKJKm7nUNQuX3uJS8BlnS_HqJZoIbjRfHEkC2bXEhT7o2NBwMb4Cr87CBQIDjKsXQs8P6bTDsUH96YC4IOiqCL9kVsl6S2rPXd-WDGjk1p9Eqeq6dNUKZVrhalk_dkbsFO6fvGVK3iI8ARoJYbrCThL8EFEUqY58iAYLjDnmmaJH8fN0Odi-QOCLolKiAXg3idpp_hbkqIsncxcmVXYOOSvm1xpMTxq93rV8w_hDEA4Sxi4EjSiom6m3uTWHo0CBtLPa=w1366-h617\",\"tegs\":[\"Все меню\", \"Салаты\"] }]}"

    @Before
    fun setUp(){
        mockWebServer = MockWebServer()
        dishesService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(DishesService::class.java)
    }

    @After
    fun tearDown(){
        mockWebServer.shutdown()
    }

    @Test
    fun testGetDishes() = runTest{
        val mockResponse = MockResponse()
        mockResponse.setBody(json)
        mockWebServer.enqueue(mockResponse)

        val response = dishesService.getDishes().body()?.dishes
        mockWebServer.takeRequest()

        val dishData1 = DishData(1,"Рис с овощами",799,560,"В китайской кухне рис с овощами готовят с добавлением грибов, бамбуковых побегов, моркови, лука и гороха. Эти ингредиенты быстро обжариваются на сковороде с соевым соусом и специями.", "https://lh3.googleusercontent.com/fife/APg5EOb3-gGunBAb_3E7L5qZLGIx0Wm8kh4UjNn2yow-7Kvf50D7eFb9Iw5g_7W7TQLGKF29-G6VNa7dHS_zEWY8VaSMh9EqUql8UEFISB_WWgiO8nf_mt0YtUhsWFB5uzw-Bfi_eS9Cs-0vLUMiqaqTGgGFDvVhUvak4AypPMEbt2-3mEkxeZNcClEy29x8gEmUU6e9G8s5GDyCxR404OIsgnGHqtIaGIGD7afRoz7PtgtmZdlXC5v7dHujDNh5l28v249qxjpa1rqxoorBb-ywkRsu1bzqDEHGbRDnFNLXHwEGlHoLS1krz9KcD3opkmQckg7-m7PXzEhnQlayqMPiNGP-WpnFYrthFVfJ0TY4zsYFx1azSJZTLa59Xuqr32eagNn9xF6mCPpGqRbunBKrbD-oWidQ_iAXZRRrgjZrv280Joe8z73AluN0A-mXcqRkpVwQo1n3szZ00wX7sD44PriRwHGUfEenTC5IyLVv3MBsYMNXJ9ALa6FZgTrnUhO_ePGoPftELYGNp4yn-xkzfM_GvnQR8A6od0bR8AqqpNketd0kBavJTkaJXBwsfvxinTdLfOTfWsZAhF97XYNEA_9SmTgnnOjt3N8YUxmUeWcor174r7bNdDuDQqq7vWRgFZNorh5v6LANRk0CVsq6B9tVxP2R1zTCc1yXgpu4kNGJsEvNyxMRy-yM3cBvhu01ZUFSalvwAqcS2M9_eBoPCu00KVtcEvyFPoqm_QNHBEkDLjUdtJC7BGcmv7SPa-rV6oH_3zeIYstyKLrgN-Dzewe816A6J7IN4YxSvCIOWbNV9Q6O3hxua_ZrGSk6ijSoBKE0XHUC04cr1O6BzRxL9lwVUhzvBzYfzmBzMPb84Pq-WwBtfCxN2j34NReGu5iABuA1iDNgz0r8WE2Dvvz0XsD6Uc8neX57A7_19J6vyJEhuARJREXGs4tFWHMmjqg-xwYOOPJhxF1BkCaQrUEKtFYBq1pT2N7_h9fMV8JOLP92grJFHRP6TmNrEjPAf9HjfYRFouBAqFMRy614VrJu5hyweBcy-4WbJNYqrvKZH_bXIQyZ6qlv4omHhGTSMMT9cAYTyiMm12bEH5ccThAygPaXlfx6ydA3towLnMpoq0ieByM2-Nql2uh4xPxgAHcmzipRgEqlYDflDKNSfeTVFKKQ4vtTWa43wR505BTjdO3mk5CVoK4sOzwcF1mQA2joVXdW63wbUWtw4wtfa3e9EP1TV01b5M02KKPcr2yxZpNQCo8-Igp6M8t_vqWSKlkq-Z7NaRswW-xQyuFWjCufwgpd1m8i5Z4tnL72DhelmIiI2cXufJb70_eobyMV5VsMUab1nLKWoKwggolnEjl2A1PLrv6T0aRTYmjt3JqFDEV58aIjZhhyrcX6h6Bb_AJzc-OFvFPw1uAuswJaimihZUfYoVuwtNRI27BD7KGnXzMHKthOM4fArK3ICIt61g91DNHAu9qhpWxsj0FrcmujdWE2vJpBf7XoOcgxiRlRKpljQ2c3M6ULTdS1nuypZleouOtwIHOUoxGtT5HJeC8ZcsgI3przMt97iGmSv5Us7xL0j42wVNZbQnyQ6EsIyIG1ZOo2ah9CyA7RqixQUevdzbPhSnJk2w6weuuRCDkdC4H97doLyAV_=w1366-h617", listOf("Все меню", "С рисом", "Салаты"))
        val dishData2 = DishData(2,"Салат по восточному",699,360,"Салат по восточному - это здоровое и вкусное блюдо, которое мы готовим в нашем ресторане. Он состоит из свежей зелени, овощей, таких как огурцы, помидоры, красный лук и оливки, а также сыра фета и пряных специй, которые придают ему уникальный вкус и аромат. Наше блюдо хорошо сочетается с другими блюдами нашего меню, а также может служить отдельным легким обедом или перекусом.", "https://lh3.googleusercontent.com/fife/APg5EOafwbErJPvjpg_yUmo7-sLRLWZF08C8k6VCCW3lGRyMVGbSnmfUxl1sNzL0oVfwT2cBZDlCMhNi6XBmrMU3zfX_wCbuT3dXN-dVGbl5ccr0NZyKwmee6geavwEEjDPIpAI0TSSoSIW5ESD42sxXblAKnt4vpETfa4MZYRBj6_0FIY9z2iPJHViTigN-vDdCPnq5RgC3f2-HZ1Algs_e5iSbauXwknae6sINa8JiZY9XdGT-qwsXivIrNakp5X4SkYUXTPfm4B9iBBuoO2SlLzDmPU8i6gglA1hNCM6ou2A0OK4x-to0plYfCz3Gpbl3c42iNqyBDi6wgiEKNP5DwyMCwrlQvfCdiXOinp6zXHqGBhV-54nl1bySMOB7oTd-iIIiG9b-P6OkNdpeMXb6INgW2looGoM_LXbEoD9HZ_QsopwtqGF5eDbeNTm4n6D7wiZX4rCpkukKNhS48Ne-cUIPAonEs0LfBEReGhiPBAsieaGqKxCS3ovW93w72qsGehL6hg_Wmxe_LOkwu-UHXyjPbUX8LfMLcL0ZdME2VOSkNnqbnR70TPBk3SJHlH3UOfsEzkxra1voEtM9hVJHgiX8kPm5Ym2V7CPYIXxaAN268ui47S07NUu1vhTOrYcdmn2zmJR8k0l1-zcG1WAx1fgKbnqbtezZ9VfYKRlLAtlsD8h86C4yUhQt74DkbfENrHZmLzVORTlYCi40wlIlAL1UsOzvR26163HYl-ZkRA-71199d3Dxr2T2Nv5IPoJmDuJACqu6FzxT71t_Q3-ZKPzNgt7fGpDf_4Xc9dC7G9lvQKpCMv8d3AVkZjf6czQoETmC7Hf6-9GdAp4jqGGztYRmR7wN11mrucEUzg63Mh5hh5bmbt93SctR_gnlt6rbyqo1aYu0p7xw6z02kyQWspnCPpinOMxLmeOI0TIsaqjq7-2VMWD04vMsYA1uVY3gYaST8IXA62ltb1ngSF6A4Qo3FtBgm1LS2yC-KxywiWSCCWwuv0WOc79dUxMl22XcUt4J_bIB0uyjIOmqD-uPGzger-dP6ZiM8_rBhNz40pJ0gWblxWoc6tpWgbhpTPBCCK7e91t3_pmlbh56EQrsKycQHHfTIVe7clQblWHI-Y9qX12WW5jn8QgmAbeAcjEBiqGLfMpWWdCs97HTZAgNYXhikTBXcqCRayvTvZRK5SiNeDzcxSdpLRyG72H6glEyYBL96KuSB1VuylT1APbBcABbDzEvTyNZWZER_NQwu9X5qDxeLmTwyHr0xq_igNaFxMxYDyYqiaAaIuFwG5yQ9d8fnMVo4QYa1HjoB8vVJrHtd2EJltEKe8KgVxjAVQyaSI7ZWwAzJXt4_3bsIgqKzY8ZLdY-xQHtorLRa_GaCaX-KasXOKJKm7nUNQuX3uJS8BlnS_HqJZoIbjRfHEkC2bXEhT7o2NBwMb4Cr87CBQIDjKsXQs8P6bTDsUH96YC4IOiqCL9kVsl6S2rPXd-WDGjk1p9Eqeq6dNUKZVrhalk_dkbsFO6fvGVK3iI8ARoJYbrCThL8EFEUqY58iAYLjDnmmaJH8fN0Odi-QOCLolKiAXg3idpp_hbkqIsncxcmVXYOOSvm1xpMTxq93rV8w_hDEA4Sxi4EjSiom6m3uTWHo0CBtLPa=w1366-h617", listOf("Все меню", "Салаты"))
        Assert.assertEquals(dishData1, response?.get(0))
        Assert.assertEquals(dishData2, response?.get(1))
    }
}