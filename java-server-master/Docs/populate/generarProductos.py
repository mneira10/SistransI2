import numpy as np
import pandas as pd
from StringIO import StringIO

# id, nombre, descrEsp, descrIng, tPrep, costo, precio, restaurantes_id
numDatos=1000000
# numDatos=10
datos = []

productos = pd.read_csv('productos.tsv', delimiter = "	")
lenP = len(productos['product_name'])
productos['product_name'] = productos.product_name.astype(str)

for i in range(numDatos):
   temp = []
   
   
   rand = np.random.random()
   nombre = productos.iloc[int(rand*lenP//1)]['product_name']

   temp.append(i+21)
   temp.append(nombre.replace(",",""))	
   temp.append("bla bla espanol")
   temp.append("bla bla ingles")
   temp.append(int(np.random.random()*120//1))
   temp.append(np.random.random()*36885.84*1.5)
   temp.append(np.random.random()*36885.84*2)
   temp.append(int((np.random.random()*99 + 21)//1))

   datos.append(temp)
   print((i+0.0)*100/numDatos)


datos = np.array(datos)


print(datos)
# print(hombres.iloc[0]['nombre'])
# print(mujeres.iloc[0]['nombre'])
# print(apellidos.iloc[0]['apellido'])
#
#
# print(np.random.random())
# print(productos["product_name"])
fmt='%s, %s, %s, %s, %s, %s,%s, %s'
np.savetxt("genProductos.csv", datos, delimiter=",",fmt = fmt)

