import numpy as np
import pandas as pd
from StringIO import StringIO
# ID, NOMBRE, TIPO, PAGINA_WEB, ZONA, ID_ADMIN

numDatos=100
# numDatos = 10
datos = []

zonas = ["Colombia","Venezuela","Argentina","Brasil",
		"Peru","Bolivia","Chile","Ecuador","Mexico",
		"Guatemala","Nicaragua","Jamaica","Cuba",
		"Haiti","Republica Dominicana","Panama","Puerto Rico",
		"U.S.A.","Canada","Costa Rica","Paraguay","Uruguay",
		"Honduras","Belize","El Salvador","Guyana",
		"Suriname","Guyana Francesa","Las Bahamas","Barbados"]


restaurants = pd.read_csv('Restaurants.csv', delimiter=',')
lenR = len(restaurants["name"])

for i in range(numDatos):
    temp = []
    rand = np.random.random()
    nombre = restaurants.iloc[int(rand*lenR//1)]['name']

    temp.append(i+21)
    temp.append(nombre.replace(",",""))
    temp.append("Thai")
    temp.append(nombre.replace(" ", "").replace(",","")+str(i+31)+"@whatev.com")
    rand2 = np.random.random()
    temp.append(zonas[int(rand2*len(zonas)//1)])
    temp.append(int((np.random.random()*199998 +31)//1))
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
fmt='%s, %s, %s, %s, %s, %s'
np.savetxt("genRestaurants.csv", datos, delimiter=",",fmt = fmt)
# 