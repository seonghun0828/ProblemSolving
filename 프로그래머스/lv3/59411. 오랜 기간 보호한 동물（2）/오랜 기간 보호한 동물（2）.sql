SELECT outs.ANIMAL_ID, outs.NAME
    FROM ANIMAL_INS ins RIGHT OUTER JOIN ANIMAL_OUTS outs
    ON ins.ANIMAL_ID = outs.ANIMAL_ID
    WHERE outs.ANIMAL_ID IS NOT NULL
    ORDER BY (outs.datetime - ins.datetime) DESC
    LIMIT 2;