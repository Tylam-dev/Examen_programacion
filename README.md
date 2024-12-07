# Buscaminas

Este es un proyecto en Java que implementa el clásico juego del Buscaminas en consola. El juego incluye funcionalidad básica para marcar y visitar celdas, manejar las minas y verificar condiciones de victoria o derrota.

## Tabla de Contenidos

- [Características](#características)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Cómo Jugar](#cómo-jugar)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Contribuciones](#contribuciones)
- [Licencia](#licencia)

## Características

- Tablero de 10x10 celdas con 10 minas colocadas aleatoriamente.
- Opciones para **marcar** (`X`) o **visitar** (`V`) celdas.
- Sistema que revela automáticamente las celdas adyacentes seguras.
- Verificación de victoria al marcar correctamente todas las minas.
- Gestión de errores en las entradas del usuario.
- Representación visual del tablero en la consola.

## Requisitos

- **Java**: Versión 8 o superior.
- **IDE recomendado**: IntelliJ IDEA, Eclipse o cualquier editor compatible con Java.
- **JDK**: Configurado en tu entorno.

## Instalación y Ejecución

Sigue estos pasos para instalar y ejecutar el proyecto en tu máquina local:

### Prerrequisitos

Asegúrate de tener instalado lo siguiente en tu sistema:

- [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html) (versión 11 o superior).
- Un editor de texto o IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) o [VS Code](https://code.visualstudio.com/).
- [Git](https://git-scm.com/) para clonar el repositorio.

### Pasos de Instalación

1. **Clona el repositorio**:
   Abre tu terminal y ejecuta:
   ```bash
   git clone https://github.com/Tylam-dev/Examen_programacion.git
   cd buscaminas

## Cómo Jugar

El Buscaminas es un juego clásico que requiere habilidad y suerte para evitar las minas ocultas. A continuación, te explicamos cómo interactuar con el juego:

1. **Inicio del juego**:
   - Al ejecutar el programa, se mostrará el tablero inicial, con todas las celdas ocultas representadas como puntos (`.`).
   - Se proporcionarán instrucciones básicas para realizar acciones.

2. **Comandos disponibles**:
   - Para **visitar** una celda: Usa el comando `V` seguido de la coordenada de la celda (por ejemplo, `V A5`). Esto revela la celda seleccionada.
   - Para **marcar** una celda como posible mina: Usa el comando `X` seguido de la coordenada de la celda (por ejemplo, `X A5`). Esto colocará una bandera en la celda seleccionada.
   - **Nota**: Si visitas una celda marcada previamente, recibirás un error.

3. **Objetivo del juego**:
   - Marca correctamente todas las minas usando banderas (`X`) sin visitar ninguna.
   - Si visitas una celda con una mina, el juego terminará con un mensaje indicando que has perdido.

4. **Información adicional**:
   - Si visitas una celda sin mina, se revelará:
     - Un número, indicando la cantidad de minas adyacentes a esa celda.
     - Un área vacía, si no hay minas alrededor (las celdas vecinas seguras también se revelarán automáticamente).
   - Las minas están representadas por `*` en el tablero al final del juego.

5. **Victoria**:
   - Ganas el juego cuando todas las minas están correctamente marcadas con banderas y todas las celdas seguras están reveladas.

6. **Error en la entrada**:
   - Si ingresas un formato incorrecto (por ejemplo, `X 5A` en lugar de `X A5`), el programa mostrará un mensaje indicando la entrada inválida. Asegúrate de seguir el formato especificado.

¡Buena suerte y diviértete jugando al Buscaminas en consola!
