INSERT INTO `email_content` (`type`, `subject`, `body`) VALUES ('REGISTRATION', 'Confirm registration', 'Hi %s, \n\nUse the link below to confirm your AndBand registration.\n\n%s\n\nThis link will remain active for two days.');
INSERT INTO `email_content` (`type`, `subject`, `body`) VALUES ('REGISTRATION_CONFIRMATION', 'Welcome to AndBand', 'Hi %s, \n\nYou are now registered with AndBand.\n\nGo to http://andband.xyz to sign in.');
INSERT INTO `email_content` (`type`, `subject`, `body`) VALUES ('PROFILE_MESSAGE', 'You received a new message from %s', 'Hi %s, \n\nYou have received the following message from %s.\n\n%s\n\n--------------------------------------\n\nGo to http://andband.xyz to reply.');
INSERT INTO `email_content` (`type`, `subject`, `body`) VALUES ('CONNECTION_REQUEST', 'You received connection request from %s', 'Hi %s, \n\nYou have received connection request from %s.\n\nGo to http://andband.xyz to confirm the request.');
INSERT INTO `email_content` (`type`, `subject`, `body`) VALUES ('CONNECTION_CONFIRMED', 'You are now connected with %s', 'Hi %s, \n\nYou are now connected with %s.\n\nGo to http://andband.xyz to view their profile');
INSERT INTO `email_content` (`type`, `subject`, `body`) VALUES ('RESET_PASSWORD', 'Reset you password', 'Hi %s, \n\nUse the link below to reset your password.\n\n%s\n\nThe link will remain active for 1 hour.');

