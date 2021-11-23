Программа для работы с Tinkoff OpenApi

###Запуск приложения:

1. В командной строке перейти в папку проекта и выполнить команду docker-compose up для запуска
   и автоподготовки базы данных:

        cd ${project.basedir}/compose/
        docker-compose up
        
2. В командной строке, находясь в корневой папке проекта выполнить команду на запуск Spring Boot
    приложения:
    
        mvn spring-boot:run
        

###Работа
Для получения информации о инвестиционном инструменте посылаем GET запрос на 

        localhost:8080/api/tinkoff-api
    
с body в формате JSON со следующими параметрами:

        {
            "token":"dsadwadsad.weqdsa3.213213.ewqd1223.ewqdssa",        // пример токена
            "ticker":"AAPL",                                             // тикер   
            "interval":"1min",                                           // интервал подписки на свечи              
            "isSandboxEnabled":"true"                                    // параметр режима песочницы
        }

###Описание API endpoints

        https://github.com/reflash/tinkoff-python-api/blob/master/README.md#documentation-for-api-endpoints
