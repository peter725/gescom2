UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%navarra%')
WHERE LOWER(name) LIKE '%navarra%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%cantabria%')
WHERE LOWER(name) LIKE '%cantabria%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%murcia%')
WHERE LOWER(name) LIKE '%murcia%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%madrid%')
WHERE LOWER(name) LIKE '%madrid%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%valenciana%')
WHERE LOWER(name) LIKE '%valenciana%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%balears%')
WHERE LOWER(name) LIKE '%balears%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%castilla y%')
WHERE LOWER(name) LIKE '%castilla y%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%mancha%')
WHERE LOWER(name) LIKE '%mancha%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%asturias%')
WHERE LOWER(name) LIKE '%asturias%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%melilla%')
WHERE LOWER(name) LIKE '%melilla%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%ceuta%')
WHERE LOWER(name) LIKE '%ceuta%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%canarias%')
WHERE LOWER(name) LIKE '%canarias%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%arag%')
WHERE LOWER(name) LIKE '%arag%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%catalu%')
WHERE LOWER(name) LIKE '%catalu%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%cantabria%')
WHERE LOWER(name) LIKE '%cantabria%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%extremadura%')
WHERE LOWER(name) LIKE '%extremadura%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%galicia%')
WHERE LOWER(name) LIKE '%galicia%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%andaluc%')
WHERE LOWER(name) LIKE '%andaluc%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%vasco%')
WHERE LOWER(name) LIKE '%vasco%';

UPDATE dbo.autonomous_community_country
SET id = (SELECT id
			FROM dbo.autonomous_community
			WHERE LOWER(name) LIKE '%rioja%')
WHERE LOWER(name) LIKE '%rioja%';