package ar.edu.unahur.obj2.vendedores

class CentroDistribucion(val ciudad:Ciudad){

    val vendedores = mutableListOf<Vendedor>()

    fun agregarVendedor(vendedor:Vendedor){
        if(vendedores.contains(vendedor)) {
            throw Exception("No se puede agregar dos veces el mismo vendedor")
        }
        vendedores.add(vendedor)
    }

    fun vendedorEstrella() : Vendedor? = vendedores.maxBy { it.puntajeCertificaciones() }

    fun puedeCubrirCiudad(ciudad:Ciudad) : Boolean = vendedores.any { it.puedeTrabajarEn(ciudad) }

    fun vendedoresGenericos() : List<Vendedor> = vendedores.filter { it.otrasCertificaciones() >= 1 }

    fun esRobusto() : Boolean = vendedores.count{ it.esFirme() } >= 3
}