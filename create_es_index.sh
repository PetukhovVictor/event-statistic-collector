curl -XPUT 'localhost:9200/events'

curl -XPUT 'localhost:9200/events/time_point/_mapping?pretty' -H 'Content-Type: application/json' -d'
{
    "time_point": {
      "properties": {
        "date": {
          "type": "date"
        }
      }
    }
}
'