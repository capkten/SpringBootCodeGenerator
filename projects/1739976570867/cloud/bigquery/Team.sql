
SELECT * FROM 'your_project.your_dataset.team' t
order by t.id desc
LIMIT 100
;

SELECT * FROM 'your_project.your_dataset.team_error_records' t
order by t.timestamp desc
LIMIT 100
;

bigquery table -> SCHEMA -> Edit as text , then input below text:
[
    {"name":"id",type:"STRING","mode":"NULLABLE","description": "id - id"}
]