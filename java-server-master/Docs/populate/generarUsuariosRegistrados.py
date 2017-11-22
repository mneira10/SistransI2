import numpy as np
import pandas as pd
from StringIO import StringIO


numDatos=600000
# numDatos=10
datos = []
# ID, LOGIN, PASSWORD, TIPO


for i in range(numDatos):
    temp = []
    temp.append(i+31)
    temp.append("usuario"+str(i))
    temp.append("password")
    if(i<numDatos/3.0):
        a = "RESTAURANTE"
        temp.append("RESTAURANTE")
    elif(i<2.0*numDatos/3.0):
        a = "ADMIN"
        temp.append("ADMIN")
    else:
        a = "usuario"
        temp.append("USUARIO")
    datos.append(temp)
    print(str((i+0.0)*100/numDatos) + " " + a )


datos = np.array(datos)


# print(datos)
# print(hombres.iloc[0]['nombre'])
# print(mujeres.iloc[0]['nombre'])
# print(apellidos.iloc[0]['apellido'])
#
#
# print(np.random.random())
fmt='%s, %s, %s, %s'
np.savetxt("usuariosRegistrados.csv", datos, delimiter=",",fmt = fmt)
