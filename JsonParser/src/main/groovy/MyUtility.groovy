import com.google.gson.Gson

class MyUtility
{
    def util (m, def key) {
        m.with{
            if (it instanceof Map) {
                [it.get(key)] + it.values().collect {util(it, key)}
            } else if (it instanceof ArrayList) {
                it.each {util(it, key)}.collect {util(it, key)}
            } else {
                []
            }
        }.flatten()
    }

    def utility(String json,String key) {
        def gsonFrom = new Gson()
        def mapFromJson = gsonFrom.fromJson(json, Map.class)
        util(mapFromJson, key).findAll {it != null}
    }

}


