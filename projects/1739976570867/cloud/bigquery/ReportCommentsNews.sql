
SELECT * FROM 'your_project.your_dataset.report_comments_news' t
order by t.id desc
LIMIT 100
;

SELECT * FROM 'your_project.your_dataset.report_comments_news_error_records' t
order by t.timestamp desc
LIMIT 100
;

bigquery table -> SCHEMA -> Edit as text , then input below text:
[
    {"name":"cn_id",type:"STRING","mode":"NULLABLE","description": "cnId - cn_id"}
]