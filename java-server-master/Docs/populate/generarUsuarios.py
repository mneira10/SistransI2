import numpy as np
import pandas as pd
from StringIO import StringIO


numDatos=1000000
datos = []


hombres = pd.read_csv('hombres.csv', delimiter=',')
mujeres = pd.read_csv('mujeres.csv',delimiter=',')
apellidos = pd.read_csv('apellidos.csv',delimiter=',')
lenh = (len(hombres.nombre))
lenm = (len(mujeres.nombre))
lena = (len(apellidos.apellido))

for i in range(numDatos):
    temp = []
    temp.append(i+31)
    genero = np.random.random()
    if(genero<0.5):
        temp.append(hombres.iloc[int(np.random.random()*lenh//1)]['nombre'])
    else:
        temp.append(mujeres.iloc[int(np.random.random()*lenm//1)]['nombre'])
    temp.append(apellidos.iloc[int(np.random.random()*lena//1)]['apellido'])
    temp.append("CC")
    temp.append("1000000000"+str(i+31))
    temp.append("email"+str(i+31)+"@whatev.com")
    datos.append(temp)
    print((i+0.0)*100/numDatos)


datos = np.array(datos)


fmt='%s, %s, %s, %s, %s, %s'
np.savetxt("usuarios.csv", datos, delimiter=",",fmt = fmt)
