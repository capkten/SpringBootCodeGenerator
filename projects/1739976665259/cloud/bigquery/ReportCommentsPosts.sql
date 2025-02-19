
SELECT * FROM 'your_project.your_dataset.report_comments_posts' t
order by t.id desc
LIMIT 100
;

SELECT * FROM 'your_project.your_dataset.report_comments_posts_error_records' t
order by t.timestamp desc
LIMIT 100
;

bigquery table -> SCHEMA -> Edit as text , then input below text:
[
    {"name":"cp_id",type:"STRING","mode":"NULLABLE","description": "cpId - cp_id"}
]