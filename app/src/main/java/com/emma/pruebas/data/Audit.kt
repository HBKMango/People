package com.emma.pruebas.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class Audit(
    @SerializedName("ObtenerMisAuditoriasPorFechaResult")
    val obtenerMisAuditoriasPorFechaResult : List<ObtenerMisAuditoriasPorFechaResult>
)

@Entity
data class ObtenerMisAuditoriasPorFechaResult (
    @SerializedName("AFEC_ID") val aFEC_ID : Int,
    @SerializedName("Bandera") val bandera : String,
    @SerializedName("BanderaGeo") val banderaGeo : Boolean,
    @SerializedName("CALADO") val cALADO : Int,
    @SerializedName("CecoId") val cecoId : String,
    @SerializedName("FILTRO_INVENTARIO") val fILTRO_INVENTARIO : String,
    @SerializedName("FILTRO_INVENTARIO_FAMILIA_AGRUPADO") val fILTRO_INVENTARIO_FAMILIA_AGRUPADO : String,
    @SerializedName("FolioOT") val folioOT : Int,
    @SerializedName("HASH_CHECKLIST") val hASH_CHECKLIST : Int,
    @SerializedName("ID_CARTA_PRESENTACION") val iD_CARTA_PRESENTACION : Int,
    @SerializedName("ID_CEDULA_ANALITICA") val iD_CEDULA_ANALITICA : Int,
    @SerializedName("ID_UNIDAD_NEGOCIO") val iD_UNIDAD_NEGOCIO : Int,
    @SerializedName("Latitud") val latitud : String,
    @SerializedName("Longitud") val longitud : String,
    @SerializedName("TIPO_AUDI") val tIPO_AUDI : String,
    @SerializedName("ZONA_OPERACION") val zONA_OPERACION : String?,
    @SerializedName("aceptada") val aceptada : Boolean?,
    @SerializedName("auditor_a_cargo") val auditor_a_cargo : Int?,
    @SerializedName("auditoria") val auditoria : String?,
    @SerializedName("auditoria_especial") val auditoria_especial : Boolean?,
    @SerializedName("dias_atraso") val dias_atraso : String?,
    @SerializedName("es_auditor_a_cargo") val es_auditor_a_cargo : Boolean?,
    @SerializedName("etapa") val etapa : String?,
    @SerializedName("fechaAceptacion") val fechaAceptacion : String?,
    @SerializedName("fechaEnvioRevision") val fechaEnvioRevision : String?,
    @SerializedName("fechaInicioReal") val fechaInicioReal : String?,
    @SerializedName("fechaReenvio") val fechaReenvio : String?,
    @SerializedName("fecha_fin_plan") val fecha_fin_plan : String?,
    @SerializedName("fecha_inicio_plan") val fecha_inicio_plan : String?,
    @SerializedName("fecha_inicio_real") val fecha_inicio_real : String?,
    @SerializedName("fecha_reprogramacion") val fecha_reprogramacion : String?,
    @SerializedName("folioSiniestro") val folioSiniestro : String?,
    @PrimaryKey
    @SerializedName("folio_auditoria") val folio_auditoria : Int?,
    @SerializedName("folio_etapa") val folio_etapa : Int?,
    @SerializedName("horas_planeadas") val horas_planeadas : Int?,
    @SerializedName("idMotivo") val idMotivo : Int?,
    @SerializedName("idUsuarioReenvio") val idUsuarioReenvio : String?,
    @SerializedName("id_pais") val id_pais : String?,
    @SerializedName("isPerdidaTotal") val isPerdidaTotal : String?,
    @SerializedName("motivo") val motivo : String?,
    @SerializedName("motivoEnvio") val motivoEnvio : String?,
    @SerializedName("no_economico") val no_economico : Int?,
    @SerializedName("nombreAuditorAcargo") val nombreAuditorAcargo : String?,
    @SerializedName("nombreSupervisor") val nombreSupervisor : String?,
    @SerializedName("nombreUsuarioLibera") val nombreUsuarioLibera : String?,
    @SerializedName("nombreUsuarioReenvio") val nombreUsuarioReenvio : String?,
    @SerializedName("plaza_id_pais") val plaza_id_pais : String?,
    @SerializedName("plaza_id_plaza") val plaza_id_plaza : Int?,
    @SerializedName("proceso_negocio") val proceso_negocio : Int?,
    @SerializedName("radio") val radio : Double,
    @SerializedName("sub_proceso_negocio") val sub_proceso_negocio : Int?,
    @SerializedName("sucursal") val sucursal : String?,
    @SerializedName("supervisor") val supervisor : String?,
    @SerializedName("tipo_auditoria") val tipo_auditoria : Int?,
    @SerializedName("titulo") val titulo : String?,
    @SerializedName("usuarioLibera") val usuarioLibera : String?,
    @SerializedName("zona") val zona : String?
)

sealed class Audits {

    data class Master(
        val data: Audit
    ) : Audits()

    data class Error(
        val error: String
    ) : Audits()

    data class Exception(
        val exception: String
    ) : Audits()

    object Empty : Audits()

}
