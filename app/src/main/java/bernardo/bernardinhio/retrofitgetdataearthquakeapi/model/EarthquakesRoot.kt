package bernardo.bernardinhio.retrofitgetdataearthquakeapi.model

class EarthquakesRoot {
    var features: Array<Features>? = null
    var metadata: Metadata? = null
    var bbox: Array<String>? = null
    var type: String? = null

    override fun toString(): String {
        return "ClassPojo [features = $features, metadata = $metadata, bbox = $bbox, type = $type]"
    }

    inner class Metadata {
        var generated: String? = null
        var count: String? = null
        var api: String? = null
        var title: String? = null
        var url: String? = null
        var status: String? = null

        override fun toString(): String {
            return "ClassPojo [generated = $generated, count = $count, api = $api, title = $title, url = $url, status = $status]"
        }
    }

    inner class Features {
        var geometry: Geometry? = null
        var id: String? = null
        var type: String? = null
        var properties: Properties? = null

        override fun toString(): String {
            return "ClassPojo [geometry = $geometry, id = $id, type = $type, properties = $properties]"
        }

        inner class Geometry {
            var coordinates: Array<String>? = null
            var type: String? = null
            override fun toString(): String {
                return "ClassPojo [coordinates = $coordinates, type = $type]"
            }
        }

        inner class Properties {
            var dmin: String? = null
            var code: String? = null
            var sources: String? = null
            var tz: String? = null
            var mmi: String? = null
            var type: String? = null
            var title: String? = null
            var magType: String? = null
            var nst: String? = null
            var sig: String? = null
            var tsunami: String? = null
            var mag: String? = null
            var alert: String? = null
            var gap: String? = null
            var rms: String? = null
            var place: String? = null
            var net: String? = null
            var types: String? = null
            var felt: String? = null
            var cdi: String? = null
            var url: String? = null
            var ids: String? = null
            var time: String? = null
            var detail: String? = null
            var updated: String? = null
            var status: String? = null

            override fun toString(): String {
                return "ClassPojo [dmin = $dmin, code = $code, sources = $sources, tz = $tz, mmi = $mmi, type = $type, title = $title, magType = $magType, nst = $nst, sig = $sig, tsunami = $tsunami, mag = $mag, alert = $alert, gap = $gap, rms = $rms, place = $place, net = $net, types = $types, felt = $felt, cdi = $cdi, url = $url, ids = $ids, time = $time, detail = $detail, updated = $updated, status = $status]"
            }
        }
    }
}