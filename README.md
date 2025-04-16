# Hipica

Es una aplicación, parte de la asignatura de PMDM, para gestionar reservas de paseos a caballo. Permite realizar, editar o eliminar reservas, seleccionar caballos, elegir horarios y enviar mensajes de confirmación a través de WhatsApp.

## Estructura

### **1. Clases principales UI**

#### **Fragments**
- **`AddReservaFragment`**: 
  - Fragment para añadir una nueva reserva.
  - Permite completar datos de la persona que quiere reservar y seleccionar un caballo, una fecha y una hora.
  - Valida los datos ingresados y guarda la reserva en la base de datos.
  - Envía un mensaje de confirmación por WhatsApp.

#### **Dialogs**
- **`datePickerDialogCustom`**:
  - Dialog personalizado para seleccionar fechas.
  - Solo permite seleccionar fines de semana y fechas posteriores a la actual.

#### **MainActivity**
- **`MainActivity`**:
  - Actividad principal que inicializa el `ReservaViewModel` y gestiona la navegación entre fragmentos (AddReserva, EditReserva).
 
## **2. Modelo**

#### **`Reserva`**
- Representa una reserva en la aplicación.
- Atributos:
  - `id`: Identificador único.
  - `jinete`: Nombre del cliente.
  - `telefono`: Teléfono del cliente.
  - `nombreCaballo`: Caballo seleccionado.
  - `fechaReserva`: Fecha del paseo.
  - `horaReserva`: Hora del paseo.
  - `comentario`: Comentarios adicionales.

### **3. Base de datos**

#### **`ReservaDatabase`**
- Base de datos local configurada con Room.
#### **`ReservaDao`**
- Es una interfaz que define las operaciones de acceso a la base de datos para la entidad `Reserva`.
- Utiliza Room para realizar consultas, inserciones, actualizaciones y eliminaciones.
El `ReservaDao` es utilizado por el `ReservaRepository` para interactuar con la base de datos de manera eficiente y segura.

### **4. Repositorio**

#### **`ReservaRepository`**
- Necesario para interactuar con la base de datos.
- Métodos para insertar, actualizar, eliminar y consultar reservas.

### **5. ViewModel**

#### **`ReservaViewModel`**
- Gestiona la lógica relacionada con la entidad Reserva.
- Proporciona datos a los fragment.
- Ejecuta operaciones en segundo plano para interactuar con la base de datos.

#### **`ReservaViewModelFactory`**
- Factory para crear instancias de `ReservaViewModel`.
- Necesaria para pasar parámetros personalizados como el repositorio.

### **6. ReservaAdapter**
- Extiende `RecyclerView.Adapter` para mostrar la lista de reservas en un `RecyclerView`.
- Se utiliza en el Homefragment que muestra la lista de reservas para proporcionar una experiencia de usuario fluida y eficiente.
- Contiene la tabla de reservas.

