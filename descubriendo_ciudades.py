   import sys

# Preguntas y respuestas por nivel
temario = [
    {
        "nivel": 1,
        "ciudad": "Bogotá",
        "pregunta": "¿Cuál es el sinónimo de 'feliz'?\nA) Triste\nB) Contento\nC) Lento\nRespuesta: ",
        "respuesta": "B"
    },
    {
        "nivel": 2,
        "ciudad": "Medellín",
        "pregunta": "¿Cuál es el antónimo de 'rápido'?\nA) Veloz\nB) Ligero\nC) Lento\nRespuesta: ",
        "respuesta": "C"
    },
    {
        "nivel": 3,
        "ciudad": "Cali",
        "pregunta": "¿Qué palabra es un sustantivo?\nA) Correr\nB) Mesa\nC) Alegre\nRespuesta: ",
        "respuesta": "B"
    },
    {
        "nivel": 4,
        "ciudad": "Barranquilla",
        "pregunta": "¿Cuál es el verbo en la oración: 'El perro corre rápido'?\nA) Perro\nB) Corre\nC) Rápido\nRespuesta: ",
        "respuesta": "B"
    },
    {
        "nivel": 5,
        "ciudad": "Cartagena",
        "pregunta": "¿Qué palabra es un adjetivo?\nA) Azul\nB) Saltar\nC) Casa\nRespuesta: ",
        "respuesta": "A"
    },
    {
        "nivel": 6,
        "ciudad": "Bucaramanga",
        "pregunta": "¿Cuál es el plural de 'lápiz'?\nA) Lápices\nB) Lápizs\nC) Lápizes\nRespuesta: ",
        "respuesta": "A"
    },
    {
        "nivel": 7,
        "ciudad": "Pereira",
        "pregunta": "¿Qué palabra es un verbo?\nA) Saltar\nB) Mesa\nC) Azul\nRespuesta: ",
        "respuesta": "A"
    },
    {
        "nivel": 8,
        "ciudad": "Manizales",
        "pregunta": "¿Cuál es el diminutivo de 'flor'?\nA) Florecita\nB) Florita\nC) Florcita\nRespuesta: ",
        "respuesta": "C"
    },
    {
        "nivel": 9,
        "ciudad": "Santa Marta",
        "pregunta": "¿Qué palabra es un pronombre?\nA) Ellos\nB) Casa\nC) Correr\nRespuesta: ",
        "respuesta": "A"
    },
    {
        "nivel": 10,
        "ciudad": "Cúcuta",
        "pregunta": "¿Cuál es el aumentativo de 'perro'?\nA) Perrón\nB) Perrote\nC) Perrito\nRespuesta: ",
        "respuesta": "B"
    }
]

def jugar():
    print("Bienvenido al juego lingüístico de preguntas y respuestas!\n")
    print("Debes avanzar por las principales ciudades de Colombia respondiendo correctamente.\n")
    nivel_actual = 0
    while nivel_actual < len(temario):
        pregunta = temario[nivel_actual]
        print(f"\nNivel {pregunta['nivel']}: {pregunta['ciudad']}")
        respuesta = input(pregunta["pregunta"]).strip().upper()
        if respuesta == pregunta["respuesta"]:
            if nivel_actual + 1 < len(temario):
                print(f"¡Correcto! Has desbloqueado la ciudad de {temario[nivel_actual + 1]['ciudad']}.\n")
            nivel_actual += 1
        else:
            print("Incorrecto. Intenta de nuevo.\n")
    print("\n¡Felicidades! Has completado el recorrido por las principales ciudades de Colombia.")

if __name__ == "__main__":
    jugar()
