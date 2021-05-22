// The file contents for the current environment will overwrite these during build.
// The build system defaults to the dev environment which uses `environment.ts`, but if you do
// `ng build --env=prod` then `environment.prod.ts` will be used instead.
// The list of which env maps to which file can be found in `.angular-cli.json`.

export const environment = {
  production: false,

  oauthApi: 'http://localhost:9999/oauth/',
  api: 'http://localhost:9990/',
  api_resource: 'http://localhost:9997/',
  api_accounts: 'http://localhost:9998/'

  // oauthApi: 'https://inovvet-auth-api.herokuapp.com/oauth/token',
  // api: 'https://inovvet-plataforma-api.herokuapp.com/',
};
