GRANT DBA TO SCHEMA;
CREATE OR REPLACE DIRECTORY EXAMPLE_DIRECTORY AS 'PATH';


GRANT DBA TO NEW_SCHEMA;
GRANT READ, WRITE ON DIRECTORY EXAMPLE_DIRECTORY TO NEW_SCHEMA;