package model.entities

import slick.profile.SqlProfile.ColumnOption.SqlType
// AUTO-GENERATED Slick data model
/** Stand-alone Slick data model for immediate use */
object Tables extends {
  val profile = slick.driver.MySQLDriver
} with Tables

/** Slick data model trait for extension, choice of backend or usage in the cake pattern. (Make sure to initialize this late.) */
trait Tables {
  val profile: slick.driver.JdbcProfile
  import profile.api._
  import slick.model.ForeignKeyAction
  // NOTE: GetResult mappers for plain SQL are only generated for tables where Slick knows how to map the types of all columns.
  import slick.jdbc.{GetResult => GR}

  /** DDL for all tables. Call .create to execute. */
  lazy val schema = Array(Categorias.schema, Ciudades.schema, Cupon.schema, Empresas.schema, EstadoCupon.schema, Estados.schema, Ofertas.schema, RCategoriaOferta.schema, ROfertaCuidad.schema, Usuario.schema).reduceLeft(_ ++ _)
  @deprecated("Use .schema instead of .ddl", "3.0")
  def ddl = schema

  /** Entity class storing rows of table Categorias
   *  @param idCategoria Database column id_categoria SqlType(INT), AutoInc, PrimaryKey
   *  @param nombreCategoria Database column nombre_categoria SqlType(VARCHAR), Length(100,true) */
  case class CategoriasRow(idCategoria: Int, nombreCategoria: String)
  /** GetResult implicit for fetching CategoriasRow objects using plain SQL queries */
  implicit def GetResultCategoriasRow(implicit e0: GR[Int], e1: GR[String]): GR[CategoriasRow] = GR{
    prs => import prs._
    CategoriasRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table categorias. Objects of this class serve as prototypes for rows in queries. */
  class Categorias(_tableTag: Tag) extends Table[CategoriasRow](_tableTag, "categorias") {
    def * = (idCategoria, nombreCategoria) <> (CategoriasRow.tupled, CategoriasRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idCategoria), Rep.Some(nombreCategoria)).shaped.<>({r=>import r._; _1.map(_=> CategoriasRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_categoria SqlType(INT), AutoInc, PrimaryKey */
    val idCategoria: Rep[Int] = column[Int]("id_categoria", O.AutoInc, O.PrimaryKey)
    /** Database column nombre_categoria SqlType(VARCHAR), Length(100,true) */
    val nombreCategoria: Rep[String] = column[String]("nombre_categoria", O.Length(100,varying=true))
  }
  /** Collection-like TableQuery object for table Categorias */
  lazy val Categorias = new TableQuery(tag => new Categorias(tag))

  /** Entity class storing rows of table Ciudades
   *  @param idCiudad Database column id_ciudad SqlType(INT), AutoInc, PrimaryKey
   *  @param nombreCuidad Database column nombre_cuidad SqlType(VARCHAR), Length(45,true)
   *  @param idEstado Database column id_estado SqlType(INT) */
  case class CiudadesRow(idCiudad: Int, nombreCuidad: String, idEstado: Int)
  /** GetResult implicit for fetching CiudadesRow objects using plain SQL queries */
  implicit def GetResultCiudadesRow(implicit e0: GR[Int], e1: GR[String]): GR[CiudadesRow] = GR{
    prs => import prs._
    CiudadesRow.tupled((<<[Int], <<[String], <<[Int]))
  }
  /** Table description of table ciudades. Objects of this class serve as prototypes for rows in queries. */
  class Ciudades(_tableTag: Tag) extends Table[CiudadesRow](_tableTag, "ciudades") {
    def * = (idCiudad, nombreCuidad, idEstado) <> (CiudadesRow.tupled, CiudadesRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idCiudad), Rep.Some(nombreCuidad), Rep.Some(idEstado)).shaped.<>({r=>import r._; _1.map(_=> CiudadesRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_ciudad SqlType(INT), AutoInc, PrimaryKey */
    val idCiudad: Rep[Int] = column[Int]("id_ciudad", O.AutoInc, O.PrimaryKey)
    /** Database column nombre_cuidad SqlType(VARCHAR), Length(45,true) */
    val nombreCuidad: Rep[String] = column[String]("nombre_cuidad", O.Length(45,varying=true))
    /** Database column id_estado SqlType(INT) */
    val idEstado: Rep[Int] = column[Int]("id_estado")

    /** Foreign key referencing Estados (database name fk_cuidad_estado) */
    lazy val estadosFk = foreignKey("fk_cuidad_estado", idEstado, Estados)(r => r.idEstado, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Ciudades */
  lazy val Ciudades = new TableQuery(tag => new Ciudades(tag))

  /** Entity class storing rows of table Cupon
   *  @param codigoCupon Database column codigo_cupon SqlType(INT), AutoInc, PrimaryKey
   *  @param fechaCreacion Database column fecha_creacion SqlType(TIMESTAMP)
   *  @param idOferta Database column id_oferta SqlType(INT)
   *  @param idUsuario Database column id_usuario SqlType(VARCHAR), Length(100,true)
   *  @param idEstadoCupon Database column id_estado_cupon SqlType(INT) */
  case class CuponRow(codigoCupon: Int, fechaCreacion: java.sql.Timestamp, idOferta: Int, idUsuario: String, idEstadoCupon: Int)
  /** GetResult implicit for fetching CuponRow objects using plain SQL queries */
  implicit def GetResultCuponRow(implicit e0: GR[Int], e1: GR[java.sql.Timestamp], e2: GR[String]): GR[CuponRow] = GR{
    prs => import prs._
    CuponRow.tupled((<<[Int], <<[java.sql.Timestamp], <<[Int], <<[String], <<[Int]))
  }
  /** Table description of table cupon. Objects of this class serve as prototypes for rows in queries. */
  class Cupon(_tableTag: Tag) extends Table[CuponRow](_tableTag, "cupon") {
    def * = (codigoCupon, fechaCreacion, idOferta, idUsuario, idEstadoCupon) <> (CuponRow.tupled, CuponRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(codigoCupon), Rep.Some(fechaCreacion), Rep.Some(idOferta), Rep.Some(idUsuario), Rep.Some(idEstadoCupon)).shaped.<>({r=>import r._; _1.map(_=> CuponRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column codigo_cupon SqlType(INT), AutoInc, PrimaryKey */
    val codigoCupon: Rep[Int] = column[Int]("codigo_cupon", O.AutoInc, O.PrimaryKey)
    /** Database column fecha_creacion SqlType(TIMESTAMP) */
    val fechaCreacion: Rep[java.sql.Timestamp] = column[java.sql.Timestamp]("fecha_creacion", SqlType("TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP"))
    /** Database column id_oferta SqlType(INT) */
    val idOferta: Rep[Int] = column[Int]("id_oferta")
    /** Database column id_usuario SqlType(VARCHAR), Length(100,true) */
    val idUsuario: Rep[String] = column[String]("id_usuario", O.Length(100,varying=true))
    /** Database column id_estado_cupon SqlType(INT) */
    val idEstadoCupon: Rep[Int] = column[Int]("id_estado_cupon")

    /** Foreign key referencing EstadoCupon (database name fk_cupon_estado_cupon) */
    lazy val estadoCuponFk = foreignKey("fk_cupon_estado_cupon", idEstadoCupon, EstadoCupon)(r => r.idEstadoCupon, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Ofertas (database name fk_oferta_cupon) */
    lazy val ofertasFk = foreignKey("fk_oferta_cupon", idOferta, Ofertas)(r => r.idOferta, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Usuario (database name fk_cupon_usuario) */
    lazy val usuarioFk = foreignKey("fk_cupon_usuario", idUsuario, Usuario)(r => r.correoUsuario, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Cupon */
  lazy val Cupon = new TableQuery(tag => new Cupon(tag))

  /** Entity class storing rows of table Empresas
   *  @param idEmpresa Database column id_empresa SqlType(INT), AutoInc, PrimaryKey
   *  @param nombreEmpresa Database column nombre_empresa SqlType(VARCHAR), Length(100,true)
   *  @param direccionEmpresa Database column direccion_empresa SqlType(VARCHAR), Length(300,true)
   *  @param telefonoEmpresa Database column telefono_empresa SqlType(VARCHAR), Length(45,true) */
  case class EmpresasRow(idEmpresa: Int, nombreEmpresa: String, direccionEmpresa: String, telefonoEmpresa: String)
  /** GetResult implicit for fetching EmpresasRow objects using plain SQL queries */
  implicit def GetResultEmpresasRow(implicit e0: GR[Int], e1: GR[String]): GR[EmpresasRow] = GR{
    prs => import prs._
    EmpresasRow.tupled((<<[Int], <<[String], <<[String], <<[String]))
  }
  /** Table description of table empresas. Objects of this class serve as prototypes for rows in queries. */
  class Empresas(_tableTag: Tag) extends Table[EmpresasRow](_tableTag, "empresas") {
    def * = (idEmpresa, nombreEmpresa, direccionEmpresa, telefonoEmpresa) <> (EmpresasRow.tupled, EmpresasRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idEmpresa), Rep.Some(nombreEmpresa), Rep.Some(direccionEmpresa), Rep.Some(telefonoEmpresa)).shaped.<>({r=>import r._; _1.map(_=> EmpresasRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_empresa SqlType(INT), AutoInc, PrimaryKey */
    val idEmpresa: Rep[Int] = column[Int]("id_empresa", O.AutoInc, O.PrimaryKey)
    /** Database column nombre_empresa SqlType(VARCHAR), Length(100,true) */
    val nombreEmpresa: Rep[String] = column[String]("nombre_empresa", O.Length(100,varying=true))
    /** Database column direccion_empresa SqlType(VARCHAR), Length(300,true) */
    val direccionEmpresa: Rep[String] = column[String]("direccion_empresa", O.Length(300,varying=true))
    /** Database column telefono_empresa SqlType(VARCHAR), Length(45,true) */
    val telefonoEmpresa: Rep[String] = column[String]("telefono_empresa", O.Length(45,varying=true))
  }
  /** Collection-like TableQuery object for table Empresas */
  lazy val Empresas = new TableQuery(tag => new Empresas(tag))

  /** Entity class storing rows of table EstadoCupon
   *  @param idEstadoCupon Database column id_estado_cupon SqlType(INT), PrimaryKey
   *  @param estadoCupon Database column estado_cupon SqlType(VARCHAR), Length(45,true) */
  case class EstadoCuponRow(idEstadoCupon: Int, estadoCupon: String)
  /** GetResult implicit for fetching EstadoCuponRow objects using plain SQL queries */
  implicit def GetResultEstadoCuponRow(implicit e0: GR[Int], e1: GR[String]): GR[EstadoCuponRow] = GR{
    prs => import prs._
    EstadoCuponRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table estado_cupon. Objects of this class serve as prototypes for rows in queries. */
  class EstadoCupon(_tableTag: Tag) extends Table[EstadoCuponRow](_tableTag, "estado_cupon") {
    def * = (idEstadoCupon, estadoCupon) <> (EstadoCuponRow.tupled, EstadoCuponRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idEstadoCupon), Rep.Some(estadoCupon)).shaped.<>({r=>import r._; _1.map(_=> EstadoCuponRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_estado_cupon SqlType(INT), PrimaryKey */
    val idEstadoCupon: Rep[Int] = column[Int]("id_estado_cupon", O.PrimaryKey)
    /** Database column estado_cupon SqlType(VARCHAR), Length(45,true) */
    val estadoCupon: Rep[String] = column[String]("estado_cupon", O.Length(45,varying=true))
  }
  /** Collection-like TableQuery object for table EstadoCupon */
  lazy val EstadoCupon = new TableQuery(tag => new EstadoCupon(tag))

  /** Entity class storing rows of table Estados
   *  @param idEstado Database column id_estado SqlType(INT), AutoInc, PrimaryKey
   *  @param nombreEstado Database column nombre_estado SqlType(VARCHAR), Length(45,true) */
  case class EstadosRow(idEstado: Int, nombreEstado: String)
  /** GetResult implicit for fetching EstadosRow objects using plain SQL queries */
  implicit def GetResultEstadosRow(implicit e0: GR[Int], e1: GR[String]): GR[EstadosRow] = GR{
    prs => import prs._
    EstadosRow.tupled((<<[Int], <<[String]))
  }
  /** Table description of table estados. Objects of this class serve as prototypes for rows in queries. */
  class Estados(_tableTag: Tag) extends Table[EstadosRow](_tableTag, "estados") {
    def * = (idEstado, nombreEstado) <> (EstadosRow.tupled, EstadosRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idEstado), Rep.Some(nombreEstado)).shaped.<>({r=>import r._; _1.map(_=> EstadosRow.tupled((_1.get, _2.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_estado SqlType(INT), AutoInc, PrimaryKey */
    val idEstado: Rep[Int] = column[Int]("id_estado", O.AutoInc, O.PrimaryKey)
    /** Database column nombre_estado SqlType(VARCHAR), Length(45,true) */
    val nombreEstado: Rep[String] = column[String]("nombre_estado", O.Length(45,varying=true))
  }
  /** Collection-like TableQuery object for table Estados */
  lazy val Estados = new TableQuery(tag => new Estados(tag))

  /** Entity class storing rows of table Ofertas
   *  @param idOferta Database column id_oferta SqlType(INT), AutoInc, PrimaryKey
   *  @param nombreOferta Database column nombre_oferta SqlType(VARCHAR), Length(100,true)
   *  @param descripcionOferta Database column descripcion_oferta SqlType(VARCHAR), Length(200,true)
   *  @param precioArticulo Database column precio_articulo SqlType(DECIMAL)
   *  @param idEmpresa Database column id_empresa SqlType(INT) */
  case class OfertasRow(idOferta: Int, nombreOferta: String, descripcionOferta: String, precioArticulo: scala.math.BigDecimal, idEmpresa: Int)
  /** GetResult implicit for fetching OfertasRow objects using plain SQL queries */
  implicit def GetResultOfertasRow(implicit e0: GR[Int], e1: GR[String], e2: GR[scala.math.BigDecimal]): GR[OfertasRow] = GR{
    prs => import prs._
    OfertasRow.tupled((<<[Int], <<[String], <<[String], <<[scala.math.BigDecimal], <<[Int]))
  }
  /** Table description of table ofertas. Objects of this class serve as prototypes for rows in queries. */
  class Ofertas(_tableTag: Tag) extends Table[OfertasRow](_tableTag, "ofertas") {
    def * = (idOferta, nombreOferta, descripcionOferta, precioArticulo, idEmpresa) <> (OfertasRow.tupled, OfertasRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idOferta), Rep.Some(nombreOferta), Rep.Some(descripcionOferta), Rep.Some(precioArticulo), Rep.Some(idEmpresa)).shaped.<>({r=>import r._; _1.map(_=> OfertasRow.tupled((_1.get, _2.get, _3.get, _4.get, _5.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_oferta SqlType(INT), AutoInc, PrimaryKey */
    val idOferta: Rep[Int] = column[Int]("id_oferta", O.AutoInc, O.PrimaryKey)
    /** Database column nombre_oferta SqlType(VARCHAR), Length(100,true) */
    val nombreOferta: Rep[String] = column[String]("nombre_oferta", O.Length(100,varying=true))
    /** Database column descripcion_oferta SqlType(VARCHAR), Length(200,true) */
    val descripcionOferta: Rep[String] = column[String]("descripcion_oferta", O.Length(200,varying=true))
    /** Database column precio_articulo SqlType(DECIMAL) */
    val precioArticulo: Rep[scala.math.BigDecimal] = column[scala.math.BigDecimal]("precio_articulo")
    /** Database column id_empresa SqlType(INT) */
    val idEmpresa: Rep[Int] = column[Int]("id_empresa")

    /** Foreign key referencing Empresas (database name fk_oferta_empresa) */
    lazy val empresasFk = foreignKey("fk_oferta_empresa", idEmpresa, Empresas)(r => r.idEmpresa, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table Ofertas */
  lazy val Ofertas = new TableQuery(tag => new Ofertas(tag))

  /** Entity class storing rows of table RCategoriaOferta
   *  @param idRCategoriaOferta Database column id_r_categoria_oferta SqlType(INT), AutoInc, PrimaryKey
   *  @param idCategoria Database column id_categoria SqlType(INT)
   *  @param idOferta Database column id_oferta SqlType(INT) */
  case class RCategoriaOfertaRow(idRCategoriaOferta: Int, idCategoria: Int, idOferta: Int)
  /** GetResult implicit for fetching RCategoriaOfertaRow objects using plain SQL queries */
  implicit def GetResultRCategoriaOfertaRow(implicit e0: GR[Int]): GR[RCategoriaOfertaRow] = GR{
    prs => import prs._
    RCategoriaOfertaRow.tupled((<<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table r_categoria_oferta. Objects of this class serve as prototypes for rows in queries. */
  class RCategoriaOferta(_tableTag: Tag) extends Table[RCategoriaOfertaRow](_tableTag, "r_categoria_oferta") {
    def * = (idRCategoriaOferta, idCategoria, idOferta) <> (RCategoriaOfertaRow.tupled, RCategoriaOfertaRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idRCategoriaOferta), Rep.Some(idCategoria), Rep.Some(idOferta)).shaped.<>({r=>import r._; _1.map(_=> RCategoriaOfertaRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_r_categoria_oferta SqlType(INT), AutoInc, PrimaryKey */
    val idRCategoriaOferta: Rep[Int] = column[Int]("id_r_categoria_oferta", O.AutoInc, O.PrimaryKey)
    /** Database column id_categoria SqlType(INT) */
    val idCategoria: Rep[Int] = column[Int]("id_categoria")
    /** Database column id_oferta SqlType(INT) */
    val idOferta: Rep[Int] = column[Int]("id_oferta")

    /** Foreign key referencing Categorias (database name fk_r_categoria) */
    lazy val categoriasFk = foreignKey("fk_r_categoria", idCategoria, Categorias)(r => r.idCategoria, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Ofertas (database name fk_r_oferta) */
    lazy val ofertasFk = foreignKey("fk_r_oferta", idOferta, Ofertas)(r => r.idOferta, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table RCategoriaOferta */
  lazy val RCategoriaOferta = new TableQuery(tag => new RCategoriaOferta(tag))

  /** Entity class storing rows of table ROfertaCuidad
   *  @param idROfertaCuidad Database column id_r_oferta_cuidad SqlType(INT), AutoInc, PrimaryKey
   *  @param idOferta Database column id_oferta SqlType(INT)
   *  @param idCuidad Database column id_cuidad SqlType(INT) */
  case class ROfertaCuidadRow(idROfertaCuidad: Int, idOferta: Int, idCuidad: Int)
  /** GetResult implicit for fetching ROfertaCuidadRow objects using plain SQL queries */
  implicit def GetResultROfertaCuidadRow(implicit e0: GR[Int]): GR[ROfertaCuidadRow] = GR{
    prs => import prs._
    ROfertaCuidadRow.tupled((<<[Int], <<[Int], <<[Int]))
  }
  /** Table description of table r_oferta_cuidad. Objects of this class serve as prototypes for rows in queries. */
  class ROfertaCuidad(_tableTag: Tag) extends Table[ROfertaCuidadRow](_tableTag, "r_oferta_cuidad") {
    def * = (idROfertaCuidad, idOferta, idCuidad) <> (ROfertaCuidadRow.tupled, ROfertaCuidadRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(idROfertaCuidad), Rep.Some(idOferta), Rep.Some(idCuidad)).shaped.<>({r=>import r._; _1.map(_=> ROfertaCuidadRow.tupled((_1.get, _2.get, _3.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column id_r_oferta_cuidad SqlType(INT), AutoInc, PrimaryKey */
    val idROfertaCuidad: Rep[Int] = column[Int]("id_r_oferta_cuidad", O.AutoInc, O.PrimaryKey)
    /** Database column id_oferta SqlType(INT) */
    val idOferta: Rep[Int] = column[Int]("id_oferta")
    /** Database column id_cuidad SqlType(INT) */
    val idCuidad: Rep[Int] = column[Int]("id_cuidad")

    /** Foreign key referencing Ciudades (database name fk_cuidad) */
    lazy val ciudadesFk = foreignKey("fk_cuidad", idCuidad, Ciudades)(r => r.idCiudad, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
    /** Foreign key referencing Ofertas (database name fk_oferta) */
    lazy val ofertasFk = foreignKey("fk_oferta", idOferta, Ofertas)(r => r.idOferta, onUpdate=ForeignKeyAction.NoAction, onDelete=ForeignKeyAction.NoAction)
  }
  /** Collection-like TableQuery object for table ROfertaCuidad */
  lazy val ROfertaCuidad = new TableQuery(tag => new ROfertaCuidad(tag))

  /** Entity class storing rows of table Usuario
   *  @param correoUsuario Database column correo_usuario SqlType(VARCHAR), PrimaryKey, Length(100,true)
   *  @param nombreUsuario Database column nombre_usuario SqlType(VARCHAR), Length(45,true)
   *  @param contrasenaUsuario Database column contrasena_usuario SqlType(VARCHAR), Length(45,true)
   *  @param celularUsuario Database column celular_usuario SqlType(VARCHAR), Length(45,true) */
  case class UsuarioRow(correoUsuario: String, nombreUsuario: String, contrasenaUsuario: String, celularUsuario: String)
  /** GetResult implicit for fetching UsuarioRow objects using plain SQL queries */
  implicit def GetResultUsuarioRow(implicit e0: GR[String]): GR[UsuarioRow] = GR{
    prs => import prs._
    UsuarioRow.tupled((<<[String], <<[String], <<[String], <<[String]))
  }
  /** Table description of table usuario. Objects of this class serve as prototypes for rows in queries. */
  class Usuario(_tableTag: Tag) extends Table[UsuarioRow](_tableTag, "usuario") {
    def * = (correoUsuario, nombreUsuario, contrasenaUsuario, celularUsuario) <> (UsuarioRow.tupled, UsuarioRow.unapply)
    /** Maps whole row to an option. Useful for outer joins. */
    def ? = (Rep.Some(correoUsuario), Rep.Some(nombreUsuario), Rep.Some(contrasenaUsuario), Rep.Some(celularUsuario)).shaped.<>({r=>import r._; _1.map(_=> UsuarioRow.tupled((_1.get, _2.get, _3.get, _4.get)))}, (_:Any) =>  throw new Exception("Inserting into ? projection not supported."))

    /** Database column correo_usuario SqlType(VARCHAR), PrimaryKey, Length(100,true) */
    val correoUsuario: Rep[String] = column[String]("correo_usuario", O.PrimaryKey, O.Length(100,varying=true))
    /** Database column nombre_usuario SqlType(VARCHAR), Length(45,true) */
    val nombreUsuario: Rep[String] = column[String]("nombre_usuario", O.Length(45,varying=true))
    /** Database column contrasena_usuario SqlType(VARCHAR), Length(45,true) */
    val contrasenaUsuario: Rep[String] = column[String]("contrasena_usuario", O.Length(45,varying=true))
    /** Database column celular_usuario SqlType(VARCHAR), Length(45,true) */
    val celularUsuario: Rep[String] = column[String]("celular_usuario", O.Length(45,varying=true))
  }
  /** Collection-like TableQuery object for table Usuario */
  lazy val Usuario = new TableQuery(tag => new Usuario(tag))
}
