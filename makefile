initfe:
	cd devcenter && npm install

buildfe:
	cd devcenter && \
		npm run build && \
		cd .. && \
		rm -rf src/main/resources/static/dist && \
		mv devcenter/dist src/main/resources/static

debugfe:
	cd devcenter && npm run serve


debugbe:
	./gradlew bootRun --args='--spring.profiles.active=test'


testbe:
	./gradlew bootRun --args='--spring.profiles.active=test'
