import numpy as np
import pandas as pd
from StringIO import StringIO
# id, categoria, grupo, cantidadDisponible, maximo

numDatos=int(1000000*0.6)
# numDatos = 10
datos = []

categorias = ["plato_fuerte", "acompanamiento", "bebida"]

# restaurants = pd.read_csv('Restaurants.csv', delimiter=',')
# lenR = len(restaurants["name"])

for i in range(numDatos):
	temp = []
	rand = np.random.random()
	if((i+0.0)/numDatos < 1.0/3.0):
		categoria = categorias[0]
	elif((i+0.0)/numDatos < 2.0/3.0):
		categoria = categorias[1]
	else:
		categoria = categorias[2]

	temp.append(i+11)
	temp.append(categoria)
	temp.append(int(np.random.random()*30))
	maximo= int(np.random.random()*300)
	temp.append(int(maximo*0.8))
	temp.append(maximo)

	datos.append(temp)
	print((i+0.0)*100/numDatos)


datos = np.array(datos)

print(datos)

# print(datos)
# print(hombres.iloc[0]['nombre'])
# print(mujeres.iloc[0]['nombre'])
# print(apellidos.iloc[0]['apellido'])
#
#
# print(np.random.random())
fmt='%s, %s, %s, %s, %s'
np.savetxt("genProductosIndividuales.csv", datos, delimiter=",",fmt = fmt)
# 