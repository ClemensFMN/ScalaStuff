
import java.net._
import java.io._


val host = "www.yahoo.com"

val ad = InetAddress.getByName(host)
println(host + ": " + ad.getHostAddress)

