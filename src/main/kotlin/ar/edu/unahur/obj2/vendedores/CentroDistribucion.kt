package ar.edu.unahur.obj2.vendedores

class CentroDistribucion(val ciudad:Ciudad){

    val vendedores = mutableListOf<Vendedor>()

    fun agregarVendedor(vendedor:Vendedor){
        vendedores.add(vendedor)
    }

    fun vendedorEstrella() : Vendedor? {
        return vendedores.maxBy { it.puntajeCertificaciones() }
    }

    fun puedeCubrirCiudad(ciudad:Ciudad) : Boolean{
        return vendedores.any { it.puedeTrabajarEn(ciudad) }
    }

    fun vendedoresGenericos() : List<Vendedor>{
        return vendedores.filter { it.otrasCertificaciones() >= 1 }
    }

    fun esRobusto() : Boolean {
        return vendedores.filter { it.esFirme() }.count() >= 3
    }
}