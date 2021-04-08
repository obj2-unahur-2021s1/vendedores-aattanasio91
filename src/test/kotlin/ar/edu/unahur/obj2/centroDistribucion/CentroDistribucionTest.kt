package ar.edu.unahur.obj2.centroDistribucion

import ar.edu.unahur.obj2.vendedores.*
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldHave

class CentroDistribucionTest : DescribeSpec({
    val misiones = Provincia(1300000)
    val cordoba = Provincia(2000000)
    val sanIgnacio = Ciudad(misiones)
    val obera = Ciudad(misiones)
    val villaDolores = Ciudad(cordoba)
    val vendedorFijo = VendedorFijo(obera)
    val viajante = Viajante(listOf(misiones))

    val certificacion1 = Certificacion(true, 55)
    val certificacion2 = Certificacion(false, 50)
    vendedorFijo.agregarCertificacion(certificacion1)
    viajante.agregarCertificacion(certificacion2)


    describe("Centro de Distribucion"){
        val centroDist = CentroDistribucion(sanIgnacio)

        describe("Agregar vendedores"){
            val centroDist = CentroDistribucion(sanIgnacio)
            centroDist.vendedores.add(vendedorFijo)
            centroDist.vendedores.add(viajante)
        }

        describe("Vendedor Estrella"){
            val centroDist = CentroDistribucion(sanIgnacio)
            centroDist.vendedores.add(vendedorFijo)
            centroDist.vendedores.add(viajante)
            centroDist.vendedorEstrella().shouldBe(vendedorFijo)
        }

        describe("Puede cubrir ciudad"){
            val centroDist = CentroDistribucion(sanIgnacio)
            centroDist.vendedores.add(vendedorFijo)
            centroDist.vendedores.add(viajante)
            centroDist.puedeCubrirCiudad(obera).shouldBeTrue()
        }

        describe("Vendedores Genericos"){
            val centroDist = CentroDistribucion(sanIgnacio)
            centroDist.vendedores.add(vendedorFijo)
            centroDist.vendedores.add(viajante)
            centroDist.vendedoresGenericos().shouldContain(viajante)
        }

        describe("Es robusto"){
            val centroDist = CentroDistribucion(sanIgnacio)
            centroDist.vendedores.add(vendedorFijo)
            centroDist.vendedores.add(viajante)
            centroDist.esRobusto().shouldBeFalse()
        }
    }

})