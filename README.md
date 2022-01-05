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

###Описание Tinkoff Open Api endpoints

        https://github.com/reflash/tinkoff-python-api/blob/master/README.md#documentation-for-api-endpoints

### Что мы полезного можем получить от Yahoo Finance Api

У Yahoo Finance API (https://query1.finance.yahoo.com/v10/finance/quoteSummary/) огромное количество модулей:
    assetProfile
    incomeStatementHistory
    incomeStatementHistoryQuarterly
    balanceSheetHistory
    balanceSheetHistoryQuarterly
    cashflowStatementHistory
    cashflowStatementHistoryQuarterly
    defaultKeyStatistics
    financialData
    calendarEvents
    secFilings
    recommendationTrend
    upgradeDowngradeHistory
    institutionOwnership
    fundOwnership
    majorDirectHolders
    majorHoldersBreakdown
    insiderTransactions
    insiderHolders
    netSharePurchaseActivity
    earnings
    earningsHistory
    earningsTrend
    industryTrend
    indexTrend
    sectorTrend
   
Пример использования: https://query1.finance.yahoo.com/v10/finance/quoteSummary/$TICKER?modules=$MODULE_NAME , 
где вместо $MODULE_NAME подставляем нужный нам модуль, а вместо TICKER - соответственно, тикер.

    https://query1.finance.yahoo.com/v10/finance/quoteSummary/BK?modules=financialData

Мы испольуем ограниченное количество информации от Yahoo (параметры, которые отмечены знаком +), для собственного удобства опишу структуру JSON

defaultKeyStatistics:
    quoteSummary
        result (Array)
            defaultKeyStatistics
                enterpriseValue + 
                forwardPE +
                priceToBook +
                enterpriseToEbitda +
                trailingEps       
                forwardEps
                enterpriseToRevenue
                и многое другое...

summaryDetail
    quoteSummary
        result (Array)
            summaryDetail
                dividendYield +
                trailingPE +
                forwardPE +
                marketCap +
                priceToSalesTrailing12Months + 
                previousClose
                open
                dayLow
                dayHigh                        
                и многое другое...

financialData
        quoteSummary
            result (Array)
                financialData
                    recommendationMean +
                    returnOnEquity +
                    totalCash
                    totalCashPerShare
                    totalDebt
                    returnOnAssets
                    operatingCashflow
                    revenueGrowth
                    и многое другое...
                                
            
t.GZkWUKuay388MaOQTTnbrOPP0xgB-mSHnP8jq5Y_HtbNw5sAQlceLMgUgUz7ypiMf7l3MW414rqZTtmgRC9h4g
