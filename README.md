totobola  
========  
  
Totobola Portugal - Desdobramento, Filtros, Upload JSC  
  
------------------------------  
  
Para gerar chaves  
java com.byctet.totobola.GeraAll Conditions.txt Comb.txt  
Parametros  
IN  
Ficheiro de condições          : Conditions.txt  
OUT  
Ficheiro de chaves resultantes : Comb.txt  
  
------------------------------  
  
Para aplicar filtros  
java com.byctet.totobola.ApplyFilter Conditions.txt Comb.txt Filtered.txt CombFiltered.txt  
Parametros  
IN  
Ficheiro de condições          : Conditions.txt  
Ficheiro de chaves a filtrar   : Comb.txt  
OUT  
Ficheiro de chaves filtradas   : Filtered.txt  
Ficheiro de chaves resultantes : CombFiltered.txt  
  
------------------------------  
  
Para verificar premios  
java com.byctet.totobola.Premios Conditions.txt Saidas.txt CombFiltered.txt Premios.txt  
Parametros  
IN  
Ficheiro de condições          : Conditions.txt  
Ficheiros de chaves saidas     : Saidas.txt  
Ficheiros de chaves jogadas    : CombFiltered.txt  
OUT  
Ficheiro de prémios            : Premios.txt  
  
------------------------------  
  
Para fazer upload para o site da SCM/JSC  
  
java com.byctet.totobola.JSCCompra <pusr> <ppwd> CombFiltered.txt <s14c> <s14f>  
Parametros  
IN  
pusr                        : utilizador registado no site da SCM/JSC  
ppwd                        : password associada  
Ficheiros de chaves jogadas : CombFiltered.txt  
s14c                        : Super 14 Jogo Casa - 0, 1, ou M  
s14c                        : Super 14 Jogo Fora - 0, 1, ou M  
OUT  
Carrinho de compras carregado - limite maximo de carregamento 20 boletins  
  
