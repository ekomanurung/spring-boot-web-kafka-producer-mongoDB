db.createUser({
    user: "user",
    pwd: "user",
    roles: [
        {
            role: "readWrite",
            db: "inventory_db"
        }
    ]
})