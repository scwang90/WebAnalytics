-- 统计PV表 Visit 分组，可知 Visit 的机制
SELECT
  `server_time`,
  `idvisitor`,
  `idsite`,
  `idvisit`,
  COUNT(*)                     num,
  MIN(`time_spent_ref_action`) min_spent,
  MAX(`time_spent_ref_action`) max_spent,
  MIN(`server_time`)           max_time,
  MAX(`server_time`)           max_time
FROM `t_log_link_visit_action`
WHERE idsite = 3 AND DATE_FORMAT(`server_time`, '%y%m%d') = '151027'
GROUP BY idvisit
ORDER BY num DESC
