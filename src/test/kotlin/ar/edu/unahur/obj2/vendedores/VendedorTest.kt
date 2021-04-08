package ar.edu.unahur.obj2.vendedores

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue

class VendedorTest : DescribeSpec({
    val misiones = Provincia(1300000)
    val sanIgnacio = Ciudad(misiones)

    describe("Vendedores") {

        val certificacionProducto1 = Certificacion(true, 30)
        val certificacionProducto2 = Certificacion(true, 10)
        val certificacionProducto3 = Certificacion(true, 15)
        val otraCertificacion1 = Certificacion(false, 30)

        describe("Vendedor fijo") {
            val obera = Ciudad(misiones)
            val vendedorFijo = VendedorFijo(obera)

            describe("Puede trabajar en:") {
                it("su ciudad de origen") {
                    vendedorFijo.puedeTrabajarEn(obera).shouldBeTrue()
                }
                it("otra ciudad") {
                    vendedorFijo.puedeTrabajarEn(sanIgnacio).shouldBeFalse()
                }
            }

            describe("No es influyente") {
                vendedorFijo.esInfluyente().shouldBeFalse()
            }

            describe("Es versatil"){
                it("Con tres certificaciones, dos iguales y una distinta:"){
                    vendedorFijo.agregarCertificacion(certificacionProducto1)
                    vendedorFijo.agregarCertificacion(certificacionProducto2)
                    vendedorFijo.agregarCertificacion(otraCertificacion1)
                    vendedorFijo.esVersatil().shouldBeTrue()
                }
                it("Con menos de tres certificaciones:"){
                    val vendedorFijo2 = VendedorFijo(obera)
                    vendedorFijo2.agregarCertificacion(certificacionProducto1)
                    vendedorFijo2.agregarCertificacion(certificacionProducto2)
                    vendedorFijo2.esVersatil().shouldBeFalse()
                }
                it("Con tres certificaciones pero todas del mismo tipo:"){
                    val vendedorFijo3 = VendedorFijo(obera)
                    vendedorFijo3.agregarCertificacion(certificacionProducto1)
                    vendedorFijo3.agregarCertificacion(certificacionProducto2)
                    vendedorFijo3.agregarCertificacion(certificacionProducto3)
                    vendedorFijo3.esVersatil().shouldBeFalse()
                }
            }
            describe("Es firme:"){
                it("Con al menos 30 puntos"){
                    val vendedorFijo = VendedorFijo(obera)
                    vendedorFijo.agregarCertificacion(certificacionProducto1)
                    vendedorFijo.esFirme().shouldBeTrue()
                }
                it("Con menos de 30 puntos"){
                    val vendedorFijo = VendedorFijo(obera)
                    vendedorFijo.agregarCertificacion(certificacionProducto3)
                    vendedorFijo.esFirme().shouldBeFalse()
                }
            }
        }

        describe("Viajante") {
            val cordoba = Provincia(2000000)
            val villaDolores = Ciudad(cordoba)
            val viajante = Viajante(listOf(misiones))


            describe("puedeTrabajarEn") {
                it("una ciudad que pertenece a una provincia habilitada") {
                    viajante.puedeTrabajarEn(sanIgnacio).shouldBeTrue()
                }
                it("una ciudad que no pertenece a una provincia habilitada") {
                    viajante.puedeTrabajarEn(villaDolores).shouldBeFalse()
                }
            }

            describe("Es influyente") {
                it("en provincia con mas de 10.000 habitantes") {
                    viajante.esInfluyente().shouldBeTrue()
                }
                it("en provincia con menos de 10.000 habitantes") {
                    val cordoba = Provincia(9000)
                    val viajante = Viajante(listOf(cordoba))
                    viajante.esInfluyente().shouldBeFalse()
                }
            }
        }
        describe("Comercio corresponsal"){
            val cordoba = Provincia(2000000)
            val villaDolores = Ciudad(cordoba)
            val capillaDelMonte = Ciudad(cordoba)

            val misiones = Provincia(1300000)
            val sanIgnacio = Ciudad(misiones)

            val entreRios = Provincia(5000000)
            val generalRamirez = Ciudad(entreRios)
            val parana = Ciudad(entreRios)

            val diamante = Ciudad(entreRios)

            val buenosAires = Provincia(10000000)
            val laPlata = Ciudad(buenosAires)

            val tucuman = Provincia(200000)
            val tafiDelValle = Ciudad(tucuman)

            val ciudades = mutableListOf<Ciudad>(villaDolores, sanIgnacio, generalRamirez)
            val comercioCorresponsal = ComercioCorresponsal(ciudades)

            describe("Puede trabajar en:"){
                it("una ciudad dentro de la lista"){
                    comercioCorresponsal.puedeTrabajarEn(villaDolores).shouldBeTrue()
                }
                it("una ciudad que no se encuentra dentro de la lista"){
                    comercioCorresponsal.puedeTrabajarEn(diamante).shouldBeFalse()
                }
            }
            describe("Es influyente:"){
                it("con tres ciudades de provincias distintas"){
                    comercioCorresponsal.esInfluyente().shouldBeTrue()
                }
                it("con dos ciudades en lista"){
                    val ciudades = mutableListOf<Ciudad>(villaDolores, generalRamirez)
                    val comercioCorresponsal2 = ComercioCorresponsal(ciudades)
                    comercioCorresponsal2.esInfluyente().shouldBeFalse()
                }
                it("con 5 ciudades de distintas provincias"){

                    val ciudades = mutableListOf<Ciudad>(villaDolores, generalRamirez, sanIgnacio, laPlata, tafiDelValle)

                    val comercioCorresponsal3 = ComercioCorresponsal(ciudades)
                    comercioCorresponsal3.esInfluyente().shouldBeTrue()
                }
                it("con 5 ciudades en 2 provincias distintas"){
                    val ciudades = mutableListOf<Ciudad>(villaDolores, generalRamirez, diamante, parana, capillaDelMonte)
                    val comercioCorresponsal4 = ComercioCorresponsal(ciudades)
                    comercioCorresponsal4.esInfluyente().shouldBeTrue()
                }
                it("con 4 ciudades en 2 provincias distintas"){
                    val ciudades = mutableListOf<Ciudad>(generalRamirez, parana, diamante, villaDolores)
                    val comercioCorresponsal5 = ComercioCorresponsal(ciudades)
                    comercioCorresponsal5.esInfluyente().shouldBeFalse()
                }

            }
        }
    }
})
