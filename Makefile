run-app:
	docker images -f "label=app-name=inventory-app" -q | xargs docker rmi -f
	docker-compose -f docker-compose.yml up -d

stop-app:
	docker-compose -f docker-compose.yml down -v

.phony: run-app stop-app