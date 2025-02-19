import request from '../utils/request';

export function getAllTemplates() {
  return request({
    url: '/template/all',
    method: 'get'
  });
}

export function generateCode(data) {
  const requestData = {
    tableSql: data.sql,
    options: {
      dataType: 'sql',
      authorName: data.config.author || '',
      packageName: data.config.packageName || '',
      returnUtilSuccess: data.config.successReturn || '',
      returnUtilFailure: data.config.errorReturn || '',
      isPackageType: true,
      isSwagger: data.switches.swaggerUI,
      isAutoImport: data.switches.autoImport,
      isWithPackage: data.switches.withComments,
      isComment: true,
      isLombok: data.switches.lombok,
      ignorePrefix: data.config.prefix || '',
      tinyintTransType: data.config.tinyIntConversion || 'int',
      nameCaseType: data.config.namingType === 'camel' ? 'CamelCase' : 'UnderScoreCase',
      timeTransType: data.config.timeType || 'Date'
    }
  };

  return request({
    url: '/code/generator/all',
    method: 'post',
    data: requestData
  });
} 