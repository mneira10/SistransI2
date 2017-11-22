#600011
import numpy as np
import pandas as pd
from StringIO import StringIO
# id, categoria, grupo, cantidadDisponible, maximo

numDatos=int(1000000*0.4 - 20)
# numDatos = 10
datos = []

# categorias = ["plato_fuerte", "acompanamiento", "bebida"]

# restaurants = pd.read_csv('Restaurants.csv', delimiter=',')
# lenR = len(restaurants["name"])

for i in range(numDatos):
	temp = []
	
	temp.append(i+600011)
	
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
fmt='%s'
np.savetxt("genMenus.csv", datos, delimiter=",",fmt = fmt)
# 