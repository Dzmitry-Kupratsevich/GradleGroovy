import com.google.gson.Gson

class MyUtility
{
    def parser (m, def key) {
        m.with{
            if (it instanceof Map) {
                [it.get(key)] + it.values().collect {parser(it, key)}
            } else if (it instanceof ArrayList) {
                it.each {parser(it, key)}.collect {parser(it, key)}
            } else {
                []
            }
        }.flatten()
    }

    def utility(String json,String key) {
        def gsonFrom = new Gson()
        def mapFromJson = gsonFrom.fromJson(json, Map.class)
        parser(mapFromJson, key).findAll {it != null}
    }

}


