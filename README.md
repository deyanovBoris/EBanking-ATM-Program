# EBanking-ATM-Program

A little I/O based console ATM program I wrote in my free time to try and master some concepts that I've been learning as a student at SoftUni.

I have seen a similar ATM program developped by <a href="https://www.youtube.com/@CodeBeauty" target="_blank"> CodeBeauty on YouTube</a>(on C++, however), and I definitely was inspired by her project. 

I decided to make the project idea my own by making a login menu, where the user can log in, or register a new user. I did this by keeping the users in a list, as a way to mimick a database. To do so, I made a `Clients` class as a way to store the data for each user.

In addition to the basic "Deposit", "Withdraw", and "View Balance" functionalities, I added a "Transfer funds" option, where the user that is currently logged in to the system can transfer money over to other *existing* users. The program starts with a default user pre-initialized, but one can make as many users as they would like by logging out of the main menu and registering a new user.

If anyone is reading this, I hope you enjoy it - it's not a marvel of security procedures but I did try to just understand how to just make something like this function.
