# Hipica

E una aplicación, parte de la asignatura de PMDM, para gestionar reservas de paseos a caballo. Permite realizar, editar o eliminar reservas, seleccionar caballos, elegir horarios y enviar mensajes de confirmación a través de WhatsApp.

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

### **2. ViewModel**

#### **`ReservaViewModel`**
- Gestiona la lógica relacionada con la entidad Reserva.
- Proporciona datos a los fragment.
- Ejecuta operaciones en segundo plano para interactuar con la base de datos.

#### **`ReservaViewModelFactory`**
- Factory para crear instancias de `ReservaViewModel`.
- Necesaria para pasar parámetros personalizados como el repositorio.

### **3. Modelo**

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

### **4. Repositorio**

#### **`ReservaRepository`**
- Necesario para interactuar con la base de datos.
- Métodos para insertar, actualizar, eliminar y consultar reservas.

### **5. Base de datos**

#### **`ReservaDatabase`**
- Base de datos local configurada con Room.
- Contiene la tabla de reservas.

### **6. Recursos**

#### **Layouts**
- **`activity_main.xml`**:
  - Contiene el contenedor principal para los fragmentos.
- **`fragment_add_reserva.xml`**:
  - Diseño del formulario para añadir reservas.

#### **Menus**
- **`menu_add_reserva.xml`**:
  - Menú con la opción para guardar una reserva.

#### **Navegación**
- **`nav_graph.xml`**:
  - Configuración de la navegación entre fragmentos.

### **7. Funcionalidades principales**

1. **Añadir reservas**:
   - Validación de datos obligatorios.
   - Validación del número de teléfono (9 dígitos).
   - Selección de fechas solo en fines de semana.

2. **Enviar mensajes de WhatsApp**:
   - Mensaje de confirmación con los detalles de la reserva.
   - Uso de la API de WhatsApp para abrir la aplicación con un mensaje predefinido.

3. **Persistencia de datos**:
   - Uso de Room para almacenar las reservas localmente.
